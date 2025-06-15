package LLD_Parking_Lot.model.vehicle

import LLD_Parking_Lot.enums.VehicleType

abstract class Vehicle(
    open val id: Long,
    open val name: String,
    val vehicleType: VehicleType
)
