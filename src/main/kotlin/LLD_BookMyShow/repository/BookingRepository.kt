package LLD_BookMyShow.repository

import LLD_BookMyShow.enums.BookingStatus
import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.User
import java.util.Date


interface BookingRepository {
    fun createBooking(
        bookingByUser: User,
        bookingShow: Show,
        bookingDateAndTime: Date,
        selectedSeats: List<Seat>,
        showAudi: Audi
    )

    fun updateBooking(booking: Booking)

    fun getBooking(bookingId: String): Booking

    fun getConfirmedBooking(bookingId: String):  List<Booking>

    fun confirmBooking(show: Show, user: User): Booking?

    fun getAllUserBookings(): List<Booking>

    fun getUserBookings(userId: String): List<Booking>

    fun removeBooking(userId: String, bookingId: String)

}