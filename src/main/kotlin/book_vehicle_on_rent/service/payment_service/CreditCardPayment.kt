package book_vehicle_on_rent.service.payment_service

import book_vehicle_on_rent.model.reservation.Reservation

class CreditCardPayment : Payment {
    override fun payWith(reservation: Reservation): Boolean {
        return true
    }
}