package LLD_BookMyShow.repository

import LLD_BookMyShow.enums.SeatType
import LLD_BookMyShow.model.Seat

interface SeatRepository {
    fun createSeat(
        seatNo: Int,
        seatRow: Int,
        seatType: SeatType = SeatType.DIAMOND,
        price: Long
    )

    fun getSeat(seatId: String): Seat
    fun getAllSeats(): List<Seat>
    fun removeSeat(seatId: String)
    fun updateSeat(seat: Seat)
    fun lockSeatPermanent(seat: Seat)
    fun getAllAvailableSeats(): List<Seat>
    fun getAllBookedSeats(seatsList: List<Seat>): List<Seat>
    fun getAllBookedSeats(): List<Seat>
}