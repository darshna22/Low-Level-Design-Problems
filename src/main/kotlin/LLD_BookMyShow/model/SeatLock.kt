package LLD_BookMyShow.model

import java.time.Instant
import java.util.Date


data class SeatLock(
    val seat: Seat,
    val show: Show,
    val seatLockByUser: User,
    val seatLockTime: Date,
    val lockTimeOutInSeconds: Long
) {
    fun isLockExpired(): Boolean {
        val lockInstant: Instant = seatLockTime.toInstant().plusSeconds(lockTimeOutInSeconds)
        val currentInstant = Date().toInstant()
        return lockInstant.isBefore(currentInstant)
    }
}
