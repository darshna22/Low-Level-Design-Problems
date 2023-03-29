package LLD_BookMyShow.repository

import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.User


interface BookingRepository {

    fun addBooking(booking: Booking)

    fun removeBooking(user: User)

    fun updateBooking(booking: Booking)
    
    fun getAllUserBookings(user: User): List<Booking>?

}