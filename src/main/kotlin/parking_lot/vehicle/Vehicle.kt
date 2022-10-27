package parking_lot.vehicle

data class Vehicle(
    var vehicleNo: String,
    val vehicleType: VehicleType,
    var vehicleSize: VehicleSize = VehicleSize.MEDIUM
)