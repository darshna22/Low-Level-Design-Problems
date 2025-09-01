package LLD_BookMyShow.services

import LLD_BookMyShow.exception.BadRequestException
import LLD_BookMyShow.model.User
import LLD_BookMyShow.provider.SeatLockProvider
import LLD_BookMyShow.repository.PaymentRepository


class PaymentService(private val paymentRepository: PaymentRepository,) {
    fun paymentFailed(bookingId: String, user: User){
        paymentRepository.paymentFailed(bookingId, user)
    }
    fun paymentSuccess(bookingId: String, user: User){
        paymentRepository.paymentSuccess(bookingId, user)

    }
}