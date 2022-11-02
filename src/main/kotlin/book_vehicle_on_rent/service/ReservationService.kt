package book_vehicle_on_rent.service

import book_vehicle_on_rent.model.reservation.Reservation
import book_vehicle_on_rent.repository.ReservationRepository

class ReservationService {
    private var reservationRepository: ReservationRepository = ReservationRepository()

    fun addReservation(reservation: Reservation) {
        reservationRepository.addReservation(reservation)
    }

    fun updateReservation(reservation: Reservation) {
        reservationRepository.addReservation(reservation)
    }

    fun removeReservation(reservation: Reservation) {
        reservationRepository.removeReservation(reservation)
    }

    fun getReservationList() = reservationRepository.getReservationList()
}