package LLD_CabBooking.service

import LLD_CabBooking.model.Booking
import LLD_CabBooking.repository.BookingRepository

class BookingService {
    private val bookingRepository = BookingRepository()

    //key should be riderId
    private var bookingHistoryMap = mutableMapOf<String, List<Booking>>()

    fun addBooking(riderId: String, booking: Booking) {
        bookingRepository.addBooking(booking)
        if (bookingHistoryMap.containsKey(riderId)) {
            val bookingList = bookingHistoryMap[riderId] as MutableList
            bookingList.add(booking)
            bookingHistoryMap[riderId]=bookingList
        } else {
           val bookingList= mutableListOf<Booking>()
            bookingList.add(booking)
            bookingHistoryMap[riderId]=bookingList
        }
    }

    fun removeBooking(riderId: String) {
        bookingRepository.removeBooking(riderId)
    }

    fun updateBooking(booking: Booking) {
        bookingRepository.updateBooking(booking)

    }

    fun getBookingList(): List<Booking> {
        return bookingRepository.getBookingList()
    }

    fun getRiderHistory(riderId: String): List<Booking>? {
        return bookingHistoryMap[riderId]
    }

}