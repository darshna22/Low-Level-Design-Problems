package LLD_Snake_and_ladder.model

data class Snake(override var startPosition: Int, override var endPosition: Int) :
    Jump(startPosition = startPosition, endPosition = endPosition)