package LLD_BookMyShow.model

import java.util.Date

data class Show(
    val showId: String,
    var seatList: List<Seat>,
    val showStartTime: Long,
    val showDurationInSeconds: Long,
    val showMovie: Movie
)