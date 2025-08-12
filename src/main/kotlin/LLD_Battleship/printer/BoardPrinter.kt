package LLD_Battleship.printer

import LLD_Battleship.service.GameInitializerService

class BoardPrinter(private val gameInitializer: GameInitializerService) {

    fun displayBoards(showShips: Boolean = true) {
        gameInitializer.getPlayerList().forEach { player ->
            println("${player.playerName}'s Board:")
            player.playerBoard?.printBoard()
        }
    }
}