package LLD_Battleship.service

import LLD_Battleship.entity.Missile
import LLD_Battleship.entity.Player
import LLD_Battleship.entity.Ship

class BattleshipOperator {
    private val gameInitializer = GameInitializerService()

    fun addPlayer(player: Player) {
        gameInitializer.initializePlayer(player)
    }

    fun addBoards(gridSize: Int) {
        gameInitializer.initializeBoards(gridSize)
    }

    fun addShipList(playerNo: Int, shipList: List<Ship>) {
        gameInitializer.initializeShipList(playerNo, shipList)
    }

    fun addMissilesList(playerNo: Int, missilesList: List<Missile>) {
        gameInitializer.initializeMissilesList(playerNo, missilesList)
    }

    fun startGame() {
        val gameEngine = GameEngineService(gameInitializer)
        gameEngine.startGame()
    }

}
