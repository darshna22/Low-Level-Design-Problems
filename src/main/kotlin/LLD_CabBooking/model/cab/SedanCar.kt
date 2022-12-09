package LLD_CabBooking.model.cab

import LLD_CabBooking.enums.CabStatus
import LLD_CabBooking.model.Location


class SedanCar(
    override var cabId: String,
    override var carName: String = "Sedan",
    override var cabLocation: Location,
    override var cabStatus: CabStatus = CabStatus.FREE,
    override var cabFair: Double,
    override var cabBasePrice: Double
) : Car(
    cabId = cabId,
    carName = carName,
    cabLocation = cabLocation,
    cabStatus = cabStatus,
    cabFair = cabFair,
    cabBasePrice = cabBasePrice
) {}