package LLD_Parking_Lot.model.lot


data class ParkingLot(
    val id: Long,
    val levelList: List<Level>,
    val name: String,
    val entryGates: Int,
    val exitGates: Int,
)