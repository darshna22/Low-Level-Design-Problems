package LLD_Parking_Lot

import LLD_Parking_Lot.model.lot.Lot
import LLD_Parking_Lot.model.vehicle.Vehicle

data class ParkingReceipt(
    val parkingLotId: Long,
    var parkingLot: Lot,
    var vehicle: Vehicle,
    var entryTime: Long,
    var exitTime: Long = 0,
    var amtToBePaid: Double = 0.0
)