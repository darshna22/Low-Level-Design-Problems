package LLD_Battleship.service

import LLD_Battleship.entity.Player

class GameResultCalculatorService {
    fun getGameResult(player1: Player, player2: Player): String {
        val damageByP1 = player1.totalHits // track during game
        val damageByP2 = player2.totalHits
        println("${player1.playerName} Hits: $damageByP1")
        println("${player2.playerName} Hits: $damageByP2")

        return when {
            damageByP1 > damageByP2 -> "${player1.playerName} wins"
            damageByP2 > damageByP1 -> "${player2.playerName} wins"
            else -> "It is a draw"
        }
    }
}