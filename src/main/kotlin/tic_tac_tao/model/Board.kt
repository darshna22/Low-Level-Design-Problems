package tic_tac_tao.model

import java.lang.Exception


class Board (private val size: Int) {
    var boardMatrix: Array<Array<PlayingPiece?>> = Array(size) { arrayOfNulls(size) }


    fun addPieceToBoard(row: Int, col: Int, playingPiece: PlayingPiece): Boolean {
        try {
            if (boardMatrix[row][col] != null)
                return false
            boardMatrix[row][col] = playingPiece
        } catch (e: Exception) {
            return false
        }

        return true
    }

    fun printBoard() {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (boardMatrix[row][col] != null) {
                    print(boardMatrix[row][col]?.pieceType?.name+"   ")
                } else
                    print("    ")
                print(" | ");
            }
            println()
        }
    }

    fun getFreeCell(): ArrayList<Pair<Int, Int>> {
        val freeCells = ArrayList<Pair<Int, Int>>()
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (boardMatrix[row][col] == null) {
                    val pair = Pair(row, col)
                    freeCells.add(pair)
                }
            }
        }
        return freeCells
    }
}

