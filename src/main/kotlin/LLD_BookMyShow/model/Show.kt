package LLD_BookMyShow.model

import java.util.Date

data class Show(
    val showId: String,
    val showName: String,
    var seatList: List<Seat>,
    val showStartTime: Date,
    val showDurationInSeconds: Long,
    val showMovie: Movie,
    val showBookedLimitPerUser: Int = 5,
)