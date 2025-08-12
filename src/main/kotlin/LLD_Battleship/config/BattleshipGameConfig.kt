//package LLD_Battleship.config
//
//import LLD_Battleship.entity.Coordinate
//import LLD_Battleship.entity.Missile
//import LLD_Battleship.entity.Ship
//
//data class BattleshipGameConfig(
//    val gridSize: Int = 5,
//    val totalShips: Int = 5,
//    val p1ShipPositions: List<Coordinate> = listOf(
//        Coordinate(1, 1),
//        Coordinate(2, 0),
//        Coordinate(2, 3),
//        Coordinate(3, 4),
//        Coordinate(4, 3)
//    ),
//    val p2ShipPositions: List<Coordinate> = listOf(
//        Coordinate(0, 1),
//        Coordinate(2, 3),
//        Coordinate(3, 0),
//        Coordinate(3, 4),
//        Coordinate(4, 1)
//    ),
//    val totalMissiles: Int = 5,
//    val p1Missiles: List<Coordinate> = listOf(
//        Coordinate(0, 1),
//        Coordinate(4, 3),
//        Coordinate(2, 3),
//        Coordinate(3, 1),
//        Coordinate(4, 1)
//    ),
//    val p2Missiles: List<Coordinate> = listOf(
//        Coordinate(0, 1),
//        Coordinate(0, 0),
//        Coordinate(1, 1),
//        Coordinate(2, 3),
//        Coordinate(4, 3)
//    )
//) {
//    fun toShips(coordinates: List<Coordinate>): List<Ship> {
//        return coordinates.map { Ship(position = it) }
//    }
//
//    fun toMissiles(coordinates: List<Coordinate>): List<Missile> {
//        return coordinates.map { Missile(attackPosition = it) }
//    }
//}
