package book_vehicle_on_rent.model.reservation

import book_vehicle_on_rent.enums.ReservationStatus
import book_vehicle_on_rent.enums.ReservationType
import book_vehicle_on_rent.model.location.Location
import book_vehicle_on_rent.model.user.User
import book_vehicle_on_rent.model.vehicle.Vehicle
import java.util.*

data class Reservation(
    var storeId: Int,
    var reservationId: Int,
    var user: User,
    var vehicle: Vehicle,
    var bookingDate: Date,
    var dateBookedFrom: Date,
    var dateBookedTo: Date,
    var fromTimeStamp: Long? = null,
    var toTimeStamp: Long? = null,
    var pickUpLocation: Location?,
    var dropLocation: Location?,
    var reservationType: ReservationType,
    var reservationStatus: ReservationStatus,
    var reservationAmt: Double=0.0,
    var isAmtPaid:Boolean=false
)

