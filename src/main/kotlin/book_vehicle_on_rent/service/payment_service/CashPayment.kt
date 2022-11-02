package book_vehicle_on_rent.service.payment_service

import book_vehicle_on_rent.model.reservation.Reservation
import book_vehicle_on_rent.service.payment_service.Payment

class CashPayment : Payment {
    override fun payWith(reservation: Reservation): Boolean {
        return true
    }
}