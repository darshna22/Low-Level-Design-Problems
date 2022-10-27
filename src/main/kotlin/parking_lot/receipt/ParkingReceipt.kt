package parking_lot.receipt

import parking_lot.parkingslots.ParkingSlot
import parking_lot.vehicle.Vehicle

data class ParkingReceipt(
    val parkingLotId:Int,
    var parkingSlot: ParkingSlot,
    var vehicle: Vehicle,
    var entryTime: Long=0,
    var exitTime: Long=0,
    var amtToBePaid:Double=0.0
)