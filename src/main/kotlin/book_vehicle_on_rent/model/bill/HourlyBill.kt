package book_vehicle_on_rent.model.bill

import book_vehicle_on_rent.utility.Utility.getDaysBtwDates
import book_vehicle_on_rent.model.reservation.Reservation


class HourlyBill : Bill {

    override fun getBillAmount(reservation: Reservation): Double {
        return (reservation.vehicle.hourlyVehicleRate * getDaysBtwDates(
            reservation.dateBookedTo,
            reservation.dateBookedFrom
        )).toDouble()
    }

}