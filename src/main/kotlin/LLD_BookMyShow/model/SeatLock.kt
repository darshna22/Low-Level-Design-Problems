package LLD_BookMyShow.model

import java.util.Date

data class SeatLock(
    val seat: Seat,
    val show: Show,
    val seatLockByUser: User,
    val seatLockTime: Int,
    val lockStartTime: Date
)
