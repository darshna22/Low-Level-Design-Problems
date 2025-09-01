package LLD_BookMyShow.provider

import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.User

interface SeatLockProvider {
    fun getTemporarilyLockAllSeats(show: Show): List<Seat>
    fun getTemporarilyLockSeats(show: Show, seatsList: List<Seat>, user: User): List<Seat>
    fun validateLock(show: Show, seat: Seat, user: User): Boolean
    fun unLockSeats(show: Show, seatsList: List<Seat>, user: User)
    fun temporarilyLockSeats(show: Show, seatsList: List<Seat>, user: User)
    fun lockSeatPermanent(seat: Seat, show: Show, user: User)
    fun getAllBookedSeats(): List<Seat>
    fun getAlreadyBookedSeatsBeforeBooking(
        seatsList: List<Seat>,
        show: Show,
        user: User
    ): List<Seat>

}