package LLD_Parking_Lot.model.lot

data class Level(
    val levelId: Long,
    val capacity: Capacity,
    val listOfLots: List<Lot>
)