package book_vehicle_on_rent.repository

import book_vehicle_on_rent.model.reservation.Reservation

class ReservationRepository {
    private var mReservationList = mutableListOf<Reservation>()


    fun addReservation(reservation: Reservation) {
        mReservationList.add(reservation)
    }

    fun updateReservation(reservation: Reservation) {
        mReservationList.add(reservation)
    }

    fun removeReservation(reservation: Reservation) {
        mReservationList.remove(reservation)
    }

    fun getReservationList() = mReservationList
}