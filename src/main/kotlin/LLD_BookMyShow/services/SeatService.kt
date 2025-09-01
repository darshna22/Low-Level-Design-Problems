package LLD_BookMyShow.services

import LLD_BookMyShow.enums.SeatType
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.repository.SeatRepository

class SeatService(private val seatRepository: SeatRepository) {
    fun createSeat(
        seatNo: Int,
        seatRow: Int,
        seatType: SeatType = SeatType.DIAMOND,
        price: Long
    ) {
        seatRepository.createSeat(seatNo, seatRow, seatType, price)
    }

    fun getSeat(seatId: String): Seat {
        return seatRepository.getSeat(seatId)
    }

    fun getAllSeats(): List<Seat> {
        return seatRepository.getAllSeats()
    }

    fun removeSeat(seatId: String) {
        seatRepository.removeSeat(seatId)
    }

    fun updateSeat(seat: Seat) {
        seatRepository.updateSeat(seat)
    }


    fun getAvailableSeats(): List<Seat> {
        return seatRepository.getAllAvailableSeats()
    }



}