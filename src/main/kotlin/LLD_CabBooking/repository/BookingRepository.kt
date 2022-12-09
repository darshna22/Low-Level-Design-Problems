package LLD_CabBooking.repository

import LLD_CabBooking.model.Booking

class BookingRepository {
    //key should be riderId
    private var bookingMap = mutableMapOf<String, Booking>()

    fun addBooking(booking: Booking) {
        if (!bookingMap.containsKey(booking.bookingId)) {
            bookingMap[booking.bookingId] = booking
        }
    }

    fun removeBooking(riderId: String) {
        bookingMap.remove(riderId)
    }

    fun updateBooking(booking: Booking) {
        if (bookingMap.containsKey(booking.bookingId)) {
            bookingMap[booking.bookingId] = booking
        }
    }

    fun getBookingList(): List<Booking> {
        return bookingMap.values as List<Booking>
    }

}