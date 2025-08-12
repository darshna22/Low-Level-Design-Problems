//package LLD_Battleship.config
//
//import LLD_Battleship.service.BattleshipOperator
//import LLD_Battleship.entity.Player
//
//fun main() {
//    val config = BattleshipGameConfig()
//
//    val game = BattleshipOperator()
//
//    // Create Players
//    val p1 = Player("Player 1")
//    val p2 = Player("Player 2")
//
//    game.addPlayer(p1)
//    game.addPlayer(p2)
//
//    // Add boards
//    game.addBoards(config.gridSize)
//
//    // Add ships
//    game.addShipList(0, config.toShips(config.p1ShipPositions))
//    game.addShipList(1, config.toShips(config.p2ShipPositions))
//
//    // Add missiles
//    game.addMissilesList(0, config.toMissiles(config.p1Missiles))
//    game.addMissilesList(1, config.toMissiles(config.p2Missiles))
//
//    // Start game
//    game.startGame()
//}
