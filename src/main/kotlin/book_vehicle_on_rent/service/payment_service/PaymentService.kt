package book_vehicle_on_rent.service.payment_service

import book_vehicle_on_rent.enums.PaymentMode
import book_vehicle_on_rent.enums.ReservationStatus
import book_vehicle_on_rent.model.reservation.Reservation
import book_vehicle_on_rent.service.payment_service.CashPayment
import book_vehicle_on_rent.service.payment_service.CreditCardPayment


class PaymentService {

    fun payWith(reservation: Reservation, paymentMode: PaymentMode) {
        reservation.reservationStatus = ReservationStatus.SCHEDULED
        reservation.isAmtPaid = when (paymentMode) {
            PaymentMode.CASH -> CashPayment().payWith(reservation)
            PaymentMode.CREDIT_CARD -> CreditCardPayment().payWith(reservation)
            else -> CashPayment().payWith(reservation)
        }
    }
}