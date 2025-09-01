package LLD_BookMyShow.model

data class Audi(
    val audiId: String,
    val audiName: String,
    val showList: MutableList<Show> = mutableListOf(),
    val seatList: MutableList<Seat> = mutableListOf(),
    val totalSeat: Int = 100,
    val showSize: Int = 4
) {
    fun addShow(show: Show? = null, showList: MutableList<Show>? = null) {
        when {
            show != null -> this.showList.add(show)
            showList != null -> this.showList.addAll(showList)
        }
    }

    fun addSeat(seat: Seat? = null, seatList: MutableList<Seat>? = null) {
        seat?.let {
            this.seatList.add(seat)
        } ?: seatList?.let {
            this.seatList.addAll(seatList)
        }
    }
}
