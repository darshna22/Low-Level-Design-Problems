package LLD_Snake_and_ladder.model

import java.util.concurrent.ThreadLocalRandom


class Board(private val boardSize: Int, private var numberOfSnakes: Int, private var numberOfLadder: Int) {
    private lateinit var boardCells: Array<Array<Cell?>>

    init {
        initializeCells(boardSize)
        addSnakesLadders()
    }

    private fun initializeCells(boardSize: Int) {
        boardCells = Array(boardSize) { arrayOfNulls(boardSize) }
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                val cellObj = Cell()
                boardCells[i][j] = cellObj
            }
        }
    }

    private fun addSnakesLadders() {
        while (numberOfSnakes > 0) {
            val snakeHead = ThreadLocalRandom.current().nextInt(1, boardCells.size * boardCells.size - 1)
            val snakeTail = ThreadLocalRandom.current().nextInt(1, boardCells.size * boardCells.size - 1)
            if (snakeTail >= snakeHead) {
                continue
            }
            val cell = getCell(playerPosition = snakeHead)
            if (cell?.jump != null)
                continue
            else {
                val jump = Jump(startPosition = snakeHead, endPosition = snakeTail)
                cell?.jump = jump
                numberOfSnakes--
            }
        }

        while (numberOfLadder > 0) {
            val ladderHead = ThreadLocalRandom.current().nextInt(1, boardCells.size * boardCells.size - 1)
            val ladderTail = ThreadLocalRandom.current().nextInt(1, boardCells.size * boardCells.size - 1)
            if (ladderHead >= ladderTail) {
                continue
            }
            val cell = getCell(playerPosition = ladderHead)
            if (cell?.jump != null)
                continue
            else {
                val jump = Jump(startPosition = ladderHead, endPosition = ladderTail)
                cell?.jump = jump
                numberOfLadder--
            }
        }
    }

    fun getCell(playerPosition: Int): Cell? {
        val boardRow = playerPosition / boardCells.size
        val boardCol = playerPosition % boardCells.size
        return boardCells[boardRow][boardCol]
    }

    fun getBoardSize() = boardSize * boardSize - 1
}