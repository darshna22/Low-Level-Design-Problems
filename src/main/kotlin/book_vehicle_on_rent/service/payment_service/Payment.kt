package book_vehicle_on_rent.service.payment_service

import book_vehicle_on_rent.enums.PaymentMode
import book_vehicle_on_rent.model.reservation.Reservation
import book_vehicle_on_rent.enums.ReservationStatus

interface Payment {
    fun payWith(reservation: Reservation): Boolean
}
