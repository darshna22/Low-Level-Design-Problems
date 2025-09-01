package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.exception.BadRequestException
import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.User
import LLD_BookMyShow.provider.SeatLockProvider
import LLD_BookMyShow.repository.PaymentRepository
import LLD_BookMyShow.services.BookingService
import LLD_BookMyShow.services.PaymentService


class PaymentRepositoryImpl(
    val bookingService: BookingService,
    private val allowedRetries: Int,
    private val seatLockProvider: SeatLockProvider
) : PaymentRepository {
    private val bookingFailures: MutableMap<Booking, Int> = mutableMapOf()

    override fun paymentFailed(booking: Booking, user: User) {
        if (booking.bookingByUser != user) {
            throw BadRequestException("Booking does not belong to the user")
        }
        if (!bookingFailures.containsKey(booking)) {
            bookingFailures[booking] = 0
        }
        val currentFailuresCount = bookingFailures[booking]
        if (currentFailuresCount != null) {
            if (currentFailuresCount > allowedRetries) {
                seatLockProvider.unLockSeats(
                    booking.bookingShow,
                    booking.bookedSeats,
                    booking.bookingByUser
                )

                throw BadRequestException("Maximum retries reached for booking ${booking.bookingId}")
            }
        }
        val newFailuresCount = currentFailuresCount?.plus(1)
        newFailuresCount?.let { bookingFailures.put(booking, it) }
    }

    override fun paymentSuccess(booking: Booking, user: User) {
        bookingService.confirmBooking(booking.bookingShow, user)
    }
}