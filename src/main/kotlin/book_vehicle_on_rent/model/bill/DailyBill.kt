package book_vehicle_on_rent.model.bill

import book_vehicle_on_rent.utility.Utility.getDaysBtwDates
import book_vehicle_on_rent.model.reservation.Reservation


/*
Bill
    - getBillAmount(reservsation)
DailyBill
HourlyBill

 */
class DailyBill : Bill {

    override fun getBillAmount(reservation: Reservation): Double {
        return (reservation.vehicle.dailyVehicleRate * getDaysBtwDates(
            reservation.dateBookedTo,
            reservation.dateBookedFrom
        )).toDouble()
    }

}