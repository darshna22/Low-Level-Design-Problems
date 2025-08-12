package LLD_Battleship.service

import LLD_Battleship.board.RectangleBoard
import LLD_Battleship.entity.Missile
import LLD_Battleship.entity.Player
import LLD_Battleship.entity.Ship

class GameInitializerService {
    private val playerList = mutableListOf<Player>()

    fun initializePlayer(player: Player) {
        playerList.add(player)
    }

    fun initializeBoards(gridSize: Int) {
        playerList.forEach { player ->
            player.playerBoard = RectangleBoard(gridSize)
        }
    }

    fun initializeShipList(playerNo: Int, shipList: List<Ship>) {
        val player = playerList[playerNo]
        player.playerBoard?.placeShip(shipList)
    }

    fun initializeMissilesList(playerNo: Int, missilesList: List<Missile>) {
        val player = playerList[playerNo]
        player.playerBoard?.addMissile(missileList = missilesList)
    }

    fun getPlayerList() = playerList
}