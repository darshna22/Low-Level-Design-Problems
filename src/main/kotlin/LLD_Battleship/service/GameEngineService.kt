package LLD_Battleship.service

import LLD_Battleship.entity.PlannedAttack
import LLD_Battleship.entity.Player
import LLD_Battleship.printer.BoardPrinter

class GameEngineService(gameInitializer: GameInitializerService) {
    private val gameResultCalculator = GameResultCalculatorService()
    private val boardPrinter = BoardPrinter(gameInitializer)
    private val playerList = gameInitializer.getPlayerList()


    fun startGame() {
        while (playerList.size > 1) {
            val allMissilesEmpty = playerList.all { it.playerBoard?.isMissilesLeft() == false }
            if (allMissilesEmpty) {
                //"Tie! No missiles left for any player.
                boardPrinter.displayBoards()
                break
            }

            if (playerList.count { it.playerBoard!!.isBoardLive() } <= 1) {
                println("Game Over!")
                boardPrinter.displayBoards()
                break
            }

            playRoundSimultaneous()
        }
        println(gameResultCalculator.getGameResult(playerList[0], playerList[1]))
    }

    private fun playRoundSimultaneous() {
        val attacks = mutableListOf<PlannedAttack>()
        for (player in playerList) {
            val missile = player.playerBoard?.getMissile() ?: continue
            val target = getOpponentPlayer(player) ?: continue
            attacks.add(PlannedAttack(player, target, missile.attackPosition))
        }

        val results = mutableListOf<Pair<PlannedAttack, Boolean>>()
        for (attack in attacks) {
            val hit =
                attack.target.playerBoard?.markHit(attack.position) == true // preview, no update
            results.add(attack to hit)
        }

        for ((attack, hit) in results) {
            if (hit) {
                attack.target.totalHits++
                attack.target.playerBoard!!.markHit(attack.position) // now update
                println("${attack.attacker.playerName} hit ${attack.target.playerName} at ${attack.position}")
            } else {
                println("${attack.attacker.playerName} missed ${attack.target.playerName} at ${attack.position}")
            }
        }

        // Step 4: Remove dead boards
        playerList.removeIf { !it.playerBoard!!.isBoardLive() }
    }


    private fun getOpponentPlayer(currentPlayer: Player): Player? {
        val opponents = playerList.filter { it != currentPlayer && it.playerBoard!!.isBoardLive() }
        return opponents.randomOrNull()
    }
}