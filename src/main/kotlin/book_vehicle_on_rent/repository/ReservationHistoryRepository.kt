package book_vehicle_on_rent.repository

import book_vehicle_on_rent.model.reservation.Reservation

class ReservationHistoryRepository {
    private val reservationHistoryMap: HashMap<Int, List<Reservation>> = HashMap<Int, List<Reservation>>()

     fun createReservationHistory(reservation: Reservation) {
        if (reservationHistoryMap.contains(reservation.user.userId)) {
            val list = reservationHistoryMap[reservation.user.userId] as MutableList<Reservation>
            list.add(reservation)
            reservation.user.let { reservationHistoryMap.put(it.userId, list) }
        } else {
            val reservationList = mutableListOf<Reservation>()
            reservationList.add(reservation)
            reservation.user.let { reservationHistoryMap.put(it.userId, reservationList) }
        }
    }

    fun getReservationHistory()=reservationHistoryMap

}