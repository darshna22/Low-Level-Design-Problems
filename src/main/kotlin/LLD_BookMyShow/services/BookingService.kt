package LLD_BookMyShow.services

import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.User
import LLD_BookMyShow.repository.BookingRepository
import LLD_BookMyShow.repository.ShowRepository


class BookingService(private val bookingRepository: BookingRepository) {
    fun addBooking(booking: Booking) {
        booking.bookedSeats.map { it.confirmSeat() }
        bookingRepository.addBooking(booking)
    }

    fun removeBooking(user: User) {
        bookingRepository.removeBooking(user)
    }

    fun updateBooking(booking: Booking) {
        bookingRepository.updateBooking(booking)
    }

    fun getAllUserBookings(user: User): List<Booking>? {
        return bookingRepository.getAllUserBookings(user)
    }
}