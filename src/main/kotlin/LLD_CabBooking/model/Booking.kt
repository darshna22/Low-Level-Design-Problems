package LLD_CabBooking.model

import LLD_CabBooking.model.cab.Car

data class Booking(
    val bookingId: String,
    val rider: Rider,
    val driver: Driver,
    val paidAmt: Double,
    val rideBookingDateAndTime: Long,
    val rideExitDateAndTime: Long
)
