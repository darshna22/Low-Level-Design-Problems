package book_vehicle_on_rent.model.bill

import book_vehicle_on_rent.model.reservation.Reservation

interface Bill {
    fun getBillAmount(reservation: Reservation):Double
}