package LLD_Battleship.board

import LLD_Battleship.entity.Missile
import LLD_Battleship.entity.Coordinate
import LLD_Battleship.entity.Ship

class RectangleBoard(
    override val size: Int,
) : BattleBoard() {
    private var shipList: MutableList<Ship> = mutableListOf()
    private var missilesList: MutableList<Missile> = mutableListOf()
    private val grid = Array(size) { CharArray(size) { '_' } }

    override fun isBoardLive(): Boolean {
        return shipList.any { !it.isDestroyed }
    }

    override fun getMissile(): Missile? {
        if (missilesList.isNotEmpty()) {
            val missile = missilesList[0]
            missilesList.removeAt(0)
            return missile
        }
        return null
    }

    override fun isMissilesLeft(): Boolean {
        return missilesList.isNotEmpty()
    }

    override fun isInsideBoard(position: Coordinate): Boolean {
        return (position.x >= 0 && position.x < size && position.y >= 0 && position.y < size)
    }

    override fun markHit(position: Coordinate): Boolean {
        if (!isInsideBoard(position)) {
            throw IllegalArgumentException("Missile position is out of board")
        }

        return if (hasShipAtPosition(position)) {  // Ship present
            grid[position.x][position.y] = 'X'
            shipList.forEach { ship: Ship ->
                if (ship.position == position) {
                    ship.isDestroyed = true
                    return@forEach
                }
            }
            true                                           // Hit
        } else {
            grid[position.x][position.y] = 'O'
            false                                          // Miss
        }
    }

    override fun placeShip(shipList: List<Ship>) {
        this.shipList = shipList.toMutableList()
        for (ship in shipList) {
            grid[ship.position.x][ship.position.y] = 'B'
        }
    }

    override fun addMissile(missileList: List<Missile>) {
        this.missilesList = missileList.toMutableList()
    }

    override fun printBoard() {
        grid.forEach { charArray ->
            charArray.forEach { char ->
                print("$char ")
            }
            println()
        }
    }

    private fun hasShipAtPosition(position: Coordinate): Boolean {
        return grid[position.x][position.y] == 'B' || grid[position.x][position.y] == 'X'
    }

}