package LLD_BookMyShow.model

data class Audi(
    val audiId: String,
    val audiName: String,
    var showList: List<Show>,
    val totalSeat:Int=100,
    val showSize: Int = 4
)
