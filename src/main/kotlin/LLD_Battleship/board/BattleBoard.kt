package LLD_Battleship.board

import LLD_Battleship.entity.Coordinate
import LLD_Battleship.entity.Missile
import LLD_Battleship.entity.Ship

abstract class BattleBoard {
    abstract val size: Int
    abstract fun isInsideBoard(position: Coordinate): Boolean
    abstract fun markHit(position: Coordinate): Boolean
    abstract fun placeShip(shipList: List<Ship>)
    abstract fun addMissile(missileList: List<Missile>)
    abstract fun printBoard()
    abstract fun isBoardLive(): Boolean
    abstract fun getMissile(): Missile?
    abstract fun isMissilesLeft(): Boolean
}