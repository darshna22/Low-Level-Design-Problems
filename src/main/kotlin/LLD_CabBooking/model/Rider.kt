package LLD_CabBooking.model

data class Rider(
    val riderId: String,
    val riderName: String,
    val riderSourceLoc: Location,
    val riderDestinationLoc: Location
)