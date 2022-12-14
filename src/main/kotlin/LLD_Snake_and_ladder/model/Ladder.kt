package LLD_Snake_and_ladder.model

data class Ladder(override var startPosition: Int, override var endPosition: Int) :
    Jump(startPosition = startPosition, endPosition = endPosition)