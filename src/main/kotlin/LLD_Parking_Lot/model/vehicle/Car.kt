package LLD_Parking_Lot.model.vehicle

import LLD_Parking_Lot.enums.VehicleType

data class Car(
    override val id: Long,
    override val name: String,
) : Vehicle(id = id, name = name, vehicleType = VehicleType.CAR)
