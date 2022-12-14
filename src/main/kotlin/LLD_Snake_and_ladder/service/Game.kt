package LLD_Snake_and_ladder.service

import LLD_Snake_and_ladder.model.Dice
import LLD_Snake_and_ladder.model.Player
import LLD_Snake_and_ladder.model.Board
import java.util.*


class Game {
    private val playerList: Deque<Player> = LinkedList<Player>()
    private lateinit var board: Board
    private lateinit var dice: Dice

    init {
        initialize()
    }

    private fun initialize() {
        val player1 = Player(playerName = "player1", 0)
        val player2 = Player(playerName = "player2", 0)
        playerList.add(player1)
        playerList.add(player2)
        board = Board(10, 6, 5)
        dice = Dice(diceCount = 1)
    }

    fun startGame() {
        var winner: Player? = null
        while (winner == null) {
            val playerTurn = findPlayerTurn()
            println("player ${playerTurn.playerName} current position is ${playerTurn.currentPosition}")

            //roll the dice
            val diceNumbers = dice.rollDice()
            val playerNewPosition = playerTurn.currentPosition + diceNumbers

            //check jump
            val jumpPosition=checkJump(playerNewPosition)
            playerTurn.currentPosition = jumpPosition
            println("player ${playerTurn.playerName} new position is $jumpPosition")

            //check for winning condition
            if (playerNewPosition >= board.getBoardSize()) {
                winner = playerTurn
            }

        }
        println("player ${winner.playerName} wins game")

    }

    private fun checkJump(playerNewPosition: Int): Int {
        var cell = board.getCell(playerNewPosition)
        var playerFinalPos = 0
        while (cell?.jump != null) {
            if (cell.jump!!.startPosition == playerNewPosition) {
                val jumpBy = if (cell.jump!!.startPosition < cell.jump!!.endPosition) "Ladder" else "Snake"
                println("jump done by $jumpBy")
                playerFinalPos = cell.jump!!.endPosition
                cell = board.getCell(cell.jump!!.endPosition)
            }

        }
        return if (playerFinalPos == 0) playerNewPosition else playerFinalPos
    }

    private fun findPlayerTurn(): Player {
        val player = playerList.poll()
        playerList.addLast(player)
        return player
    }
}