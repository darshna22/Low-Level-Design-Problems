package book_vehicle_on_rent.model.vehicle

import book_vehicle_on_rent.enums.Status
import book_vehicle_on_rent.enums.VehicleType

open class Vehicle(
    var vehicleN0: Int?=null,
    var vehicleCompany: String="",
    var vehicleType: VehicleType = VehicleType.CAR,
    var noOfSeat: Int=1,
    var vehicleName:String="",
    var hourlyVehicleRate: Int=100,
    var dailyVehicleRate: Int=1000,
    var status: Status = Status.ACTIVE
)
