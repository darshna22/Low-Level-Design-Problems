package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.User
import LLD_BookMyShow.repository.BookingRepository


class BookingRepositoryImpl : BookingRepository {
    //key should be riderId
    private var userBookingMap = mutableMapOf<User, List<Booking>>()
    override fun addBooking(booking: Booking) {
        val user = booking.bookingByUser
        var bookingList = mutableListOf<Booking>()
        if (userBookingMap.containsKey(user)) {
            bookingList = userBookingMap[user] as MutableList<Booking>
        }
        bookingList.add(booking)
        userBookingMap[user] = bookingList

    }

    override fun removeBooking(user: User) {
        userBookingMap.remove(user)
    }

    override fun updateBooking(booking: Booking) {
        val user = booking.bookingByUser
        if (userBookingMap.containsKey(user)) {
            val bookingList = userBookingMap[user] as MutableList<Booking>
            bookingList.add(booking)
            userBookingMap[user] = bookingList
        }
    }
    
    override fun getAllUserBookings(user: User): List<Booking>? {
        return userBookingMap[user]
    }

}