package LLD_BookMyShow.provider

import LLD_BookMyShow.enums.SeatStatusType
import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.exception.SeatTemporarilyNotAvailableException
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.SeatLock
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.User
import java.util.Date

class InMemorySeatLockProvider(
    private val lockTimeout: Long = 60 * 1000 //1 minute
) : SeatLockProvider {
    private val locks: MutableMap<Show, MutableMap<Seat, SeatLock>> =
        HashMap()

    @Synchronized
    override fun temporarilyLockSeats(
        show: Show, seatsList: List<Seat>,
        user: User
    ) {
        for (seat in seatsList) {
            if (isSeatLocked(show, seat)) {
                throw SeatTemporarilyNotAvailableException("Seat is already locked")
            }
        }
        for (seat in seatsList) {
            lockSeat(show, seat, user, lockTimeout)
        }
    }


    override fun getAllAvailableSeats(): List<Seat> {
        return getAllSeats().filter { it.seatStatus == SeatStatusType.AVAILABLE }
    }

    override fun getTemporarilyLockSeat(seatsList: List<Seat>): List<Seat> {
        return seatsList.filter { it.seatStatus == SeatStatusType.TEMPORARY_LOCKED }
    }

    override fun getAllBookedSeats(seatsList: List<Seat>): List<Seat> {
        return seatsList.filter { it.seatStatus == SeatStatusType.CONFIRM }
    }

    override fun getAllBookedSeats(): List<Seat> {
        return getAllSeats().filter { it.seatStatus == SeatStatusType.CONFIRM }
    }

    override fun isAnySeatLockedAlready(seatsList: List<Seat>): Boolean {
        return getTemporarilyLockSeat(seatsList).isNotEmpty()
    }

    override fun lockSeatPermanent(seat: Seat, show: Show, user: User) {
        val seatLockMap = locks[show]
        if (seatLockMap?.containsKey(seat) == true && validateLock(show, seat, user)) {
            locks.remove(show)
        }
    }

    override fun getAllBookedSeats(): List<Seat> {
        TODO("Not yet implemented")
    }

    override fun getAlreadyBookedSeatsBeforeBooking(
        seatsList: List<Seat>,
        show: Show,
        user: User
    ): List<Seat> {
        TODO("Not yet implemented")
    }

    override fun unLockSeats(show: Show, seatsList: List<Seat>, user: User) {
        if (!locks.containsKey(show)) {
            throw NotFoundException("Show not found")
        }
        val seatLockMap = locks[show]
        for (seat in seatsList) {
            if (seatLockMap?.containsKey(seat) == true && validateLock(show, seat, user)) {
                unlockSeat(show, seat)
            }
        }
    }

    override fun validateLock(show: Show, seat: Seat, user: User): Boolean {
        val isSameUser = isSameUserLockSeat(show, seat, user)
        return isSeatLocked(show, seat) && isSameUser
    }

    private fun isSameUserLockSeat(
        show: Show,
        seat: Seat,
        user: User
    ) = locks[show]?.get(seat)?.seatLockByUser?.equals(user) == true

    override fun getTemporarilyLockAllSeats(show: Show): List<Seat> {
        if (!locks.containsKey(show)) {
            throw NotFoundException("Show not found")
        }
        val seatLockMap = locks[show]
        val tempLockSeatList = seatLockMap?.values?.toList()

        val lockedSeats = tempLockSeatList?.filter { seatLock ->
            (isSeatLocked(
                show,
                seat = seatLock.seat
            ) && !seatLock.isLockExpired())
        }
        return lockedSeats?.map { it.seat } ?: emptyList()
    }

    override fun getTemporarilyLockSeats(
        show: Show,
        seatsList: List<Seat>,
        user: User
    ): List<Seat> {
        val allLockedSeats = getTemporarilyLockAllSeats(show)
        val filteredLockSeats = allLockedSeats.filter { seat: Seat ->
            (seatsList.contains(seat) && isSameUserLockSeat(
                show,
                seat,
                user
            )) && isSeatLocked(show, seat)
        }
        return filteredLockSeats

    }

    private fun unlockSeat(show: Show, seat: Seat) {
        if (!locks.containsKey(show)) {
            return
        }
        locks[show]?.remove(seat)
    }

    private fun lockSeat(show: Show, seat: Seat, user: User, seatLockTimeOut: Long) {
        val seatLocksMap = locks.getOrPut(show) { HashMap() }
        seat.seatStatus = SeatStatusType.TEMPORARY_LOCKED
        seatLocksMap[seat] = SeatLock(seat, show, user, Date(), seatLockTimeOut)
    }


    private fun isSeatLocked(show: Show, seat: Seat): Boolean {
        val showLocks = locks[show] ?: return false // If show not in locks, seat isn't locked
        val seatLock =
            showLocks[seat] ?: return false // If seat not in this show's locks, it isn't locked
        return (seatLock.seat.seatId == seat.seatId && !seatLock.isLockExpired())
    }

}