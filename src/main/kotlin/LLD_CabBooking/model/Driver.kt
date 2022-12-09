package LLD_CabBooking.model

import LLD_CabBooking.enums.DriverStatus
import LLD_CabBooking.model.cab.Car

open class Driver(val driverId: String, val driverName: String, var car: Car? = null,var driverStatus:DriverStatus=DriverStatus.FREE)
