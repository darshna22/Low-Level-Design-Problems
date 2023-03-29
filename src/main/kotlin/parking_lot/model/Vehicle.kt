package parking_lot.model

import parking_lot.enums.VehicleSize
import parking_lot.enums.VehicleType

data class Vehicle(
    var vehicleNo: String,
    val vehicleType: VehicleType,
    var vehicleSize: VehicleSize = VehicleSize.MEDIUM
)