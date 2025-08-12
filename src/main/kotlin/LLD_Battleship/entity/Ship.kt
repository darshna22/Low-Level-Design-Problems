package LLD_Battleship.entity

data class Ship(
    val name: String="",
    val shipId: Int=0,
    val position: Coordinate,
    var isDestroyed: Boolean = false
)
