package LLD_BookMyShow.model

import LLD_BookMyShow.enums.BookingStatus
import java.util.Date

data class Booking(
    val bookingId: String,
    val bookingByUser: User,
    val bookingShow: Show,
    val bookingDateAndTime: Date,
    val bookedSeats: List<Seat>,
    val bookingStatus: BookingStatus = BookingStatus.CREATED,
    val price: Long? = null,
    val showAudi: Audi
)