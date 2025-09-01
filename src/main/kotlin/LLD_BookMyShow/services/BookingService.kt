package LLD_BookMyShow.services

import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.User
import LLD_BookMyShow.repository.BookingRepository
import LLD_BookMyShow.repository.ShowRepository
import java.util.Date


class BookingService(private val bookingRepository: BookingRepository) {
    fun createBooking(
        bookingByUser: User,
        bookingShow: Show,
        bookingDateAndTime: Date,
        bookedSeats: List<Seat>,
        showAudi: Audi
    ) {
        bookingRepository.createBooking(
            bookingByUser,
            bookingShow,
            bookingDateAndTime,
            bookedSeats,
            showAudi
        )
    }

    fun updateBooking(booking: Booking) {
        bookingRepository.updateBooking(booking)
    }

    fun getBooking(bookingId: String): Booking {
        return bookingRepository.getBooking(bookingId)
    }

    fun getAllUserBookings(): List<Booking> {
        return bookingRepository.getAllUserBookings()
    }

    fun getUserBookings(userId: String): List<Booking> {
        return bookingRepository.getUserBookings(userId)
    }

    fun removeBooking(userId: String, bookingId: String) {
        return bookingRepository.removeBooking(userId, bookingId)
    }

    fun confirmBooking(show: Show, user: User): Booking? {
        return bookingRepository.confirmBooking(show, user)
    }
}