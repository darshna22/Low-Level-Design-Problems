package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.enums.SeatStatusType
import LLD_BookMyShow.enums.SeatType
import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.repository.SeatRepository
import java.util.UUID

class SeatRepositoryImpl : SeatRepository {
    private val seatMap = mutableMapOf<String, Seat>()

    override fun createSeat(
        seatNo: Int,
        seatRow: Int,
        seatType: SeatType,
        price: Long
    ) {
        val seatId = UUID.randomUUID().toString()
        seatMap[seatId] = getCreatedSeat(seatId, seatNo, seatRow, seatType, price)
    }

    private fun getCreatedSeat(
        seatId: String, seatNo: Int,
        seatRow: Int,
        seatType: SeatType,
        price: Long
    ) = Seat(seatId, seatNo, seatRow, seatType, price);

    override fun getSeat(seatId: String): Seat {
        return seatMap[seatId] ?: throw NotFoundException("Seat not found with id $seatId")
    }

    override fun getAllSeats(): List<Seat> {
        return seatMap.values.toList()
    }

    override fun removeSeat(seatId: String) {
        seatMap.remove(getSeat(seatId).seatId)
    }

    override fun updateSeat(seat: Seat) {
        seatMap.remove(getSeat(seat.seatId).seatId)
        seatMap[seat.seatId] = seat
    }


    override fun lockSeatPermanent(seat: Seat) {
        getSeat(seatId = seat.seatId) // this will throw exception if seat not found
        seat.seatStatus = SeatStatusType.CONFIRM
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
}