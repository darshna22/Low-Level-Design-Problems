package LLD_BookMyShow.model

import LLD_BookMyShow.enums.CurrencyCode
import LLD_BookMyShow.enums.SeatStatusType
import LLD_BookMyShow.enums.SeatType
import LLD_BookMyShow.exception.SeatNotAvailableException

data class Seat(
    val seatId: String,
    val seatNo: Int,
    val seatRow: Int,
    val seatType: SeatType = SeatType.DIAMOND,
    val price: Long,
    val currencyCode: CurrencyCode = CurrencyCode.INR,
    var seatStatus: SeatStatusType = SeatStatusType.AVAILABLE
) {
    fun confirmSeat() {
        if (seatStatus != SeatStatusType.AVAILABLE) {
            throw SeatNotAvailableException("Seat is in $seatStatus status")
        }
        seatStatus = SeatStatusType.CONFIRM
    }
}