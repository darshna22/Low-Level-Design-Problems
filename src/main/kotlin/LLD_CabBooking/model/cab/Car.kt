package LLD_CabBooking.model.cab

import LLD_CabBooking.enums.CabFairType
import LLD_CabBooking.enums.CabStatus
import LLD_CabBooking.enums.CabType
import LLD_CabBooking.model.Location


open class Car(
    open var cabId: String,
    val isCabSharable: Boolean = false,
    open var carName: String,
    open var cabLocation: Location,
    val cabType: CabType = CabType.SMALL,
    open var cabStatus: CabStatus,
    val cabFairType: CabFairType = CabFairType.PER_HOUR,
    open var cabFair: Double,
    open var cabBasePrice: Double
)
