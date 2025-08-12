package LLD_Battleship.entity

import LLD_Battleship.board.BattleBoard

data class Player(
    val playerName: String,
    var playerId: Int=0,
    var playerBoard: BattleBoard? = null,
    var totalHits: Int = 0
)