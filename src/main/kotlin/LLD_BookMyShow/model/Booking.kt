package LLD_BookMyShow.model

import LLD_BookMyShow.enums.BookingStatus

data class Booking(
    val bookingId: String,
    val bookingByUser: User,
    val bookingShow: Show,
    val bookingDateAndTime: Long,
    val bookedSeats: List<Seat>,
    val bookingStatus: BookingStatus = BookingStatus.CREATED,
    val price: Long? = null
) {
    fun setPrice() {
        bookedSeats.map { it.price }.sum().toLong()
    }
}