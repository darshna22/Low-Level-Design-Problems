package LLD_BookMyShow.repository

import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.User

interface PaymentRepository {
    fun paymentSuccess(booking: Booking, user: User)
    fun paymentFailed(booking: Booking, user: User)
}