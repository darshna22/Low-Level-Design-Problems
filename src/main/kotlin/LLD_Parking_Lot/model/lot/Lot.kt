package LLD_Parking_Lot.model.lot

import LLD_Parking_Lot.enums.LotType

data class Lot(
    val lotId: Long,
    val lotType: LotType,
    var isLotAvailable: Boolean,
    val lotPricePerHour: Double = 10.0
)
