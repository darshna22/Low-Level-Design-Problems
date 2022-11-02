package book_vehicle_on_rent.model.bill

import book_vehicle_on_rent.model.reservation.Reservation
import book_vehicle_on_rent.enums.ReservationType


class BillService {

    fun calculateBillAmt(reservation: Reservation) {
        reservation.reservationAmt = when (reservation.reservationType) {
            ReservationType.HOURLY -> HourlyBill().getBillAmount(reservation)
            ReservationType.DAILY -> DailyBill().getBillAmount(reservation)
        }
    }
}