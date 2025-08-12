import LLD_Battleship.entity.Coordinate
import LLD_Battleship.entity.Missile
import LLD_Battleship.entity.Player
import LLD_Battleship.entity.Ship
import LLD_Battleship.service.BattleshipOperator
import java.util.Scanner
import kotlin.math.pow

fun main() {
    val scanner = Scanner(System.`in`)
    println("Let's start the battleship simulation game")

    val battleshipOperator = BattleshipOperator()

    println("Please enter number of players (minimum 2)")
    var playerSize = scanner.nextInt()
    while (playerSize < 2) {
        println("Please enter more than 1")
        playerSize = scanner.nextInt()
    }

    for (i in 0 until playerSize) {
        battleshipOperator.addPlayer(Player(playerName = "Player${i + 1}", playerId = i))
    }

    println("Please enter grid size of board (1-9)")
    val gridSize = scanner.nextInt()
    battleshipOperator.addBoards(gridSize)

    val maxShipLimit = gridSize.toDouble().pow(2.0) / 2
    println("Please enter total ships (1-$maxShipLimit)")
    val totalShip = scanner.nextInt()

    for (player in 0 until playerSize) {
        println("Enter ship positions for Player${player + 1} in format x,y (e.g., 1,1)")
        val shipList = mutableListOf<Ship>()
        for (i in 0 until totalShip) {
            val (x, y) = scanner.next().split(",").map { it.toInt() }
            shipList.add(Ship("Ship${i + 1}", i, Coordinate(x, y)))
        }
        battleshipOperator.addShipList(player, shipList)
    }

    println("Please enter total missiles (1-100)")
    val totalMissile = scanner.nextInt()

    for (player in 0 until playerSize) {
        println("Enter missile positions for Player${player + 1} in format x,y (e.g., 1,1)")
        val missileList = mutableListOf<Missile>()
        for (i in 0 until totalMissile) {
            val (x, y) = scanner.next().split(",").map { it.toInt() }
            missileList.add(Missile("Missile${i + 1}", i, Coordinate(x, y)))
        }
        battleshipOperator.addMissilesList(player, missileList)
    }

    // Game loop
    var continueGame = true
    while (continueGame) {
        battleshipOperator.startGame()

        println("Do you want to continue the game? (y/n)")
        val choice = scanner.next().lowercase()
        if (choice == "n") {
            println("Exiting game. Thanks for playing!")
            continueGame = false
        }
    }
}
