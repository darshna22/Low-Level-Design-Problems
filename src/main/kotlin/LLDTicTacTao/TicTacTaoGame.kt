package LLDTicTacTao

import LLDTicTacTao.model.*
import java.util.*

class TicTacTaoGame {
    private val playerList: Deque<Player> = LinkedList<Player>()
    private lateinit var board: Board

    init {
        initialize()
    }

    private fun initialize() {
        val player1 = Player(playerName = "Player1", playingPiece = PlayingPieceO())
        val player2 = Player(playerName = "Player2", playingPiece = PlayingPieceX())
        playerList.add(player1)
        playerList.add(player2)

        board = Board(size = 3)

    }

    fun startGame(): String {
        var noWinner = true
        while (noWinner) {
            val playerTurn: Player = playerList.removeFirst()
            board.printBoard()
            val freeCells: ArrayList<Pair<Int, Int>> = board.getFreeCell()

            if (freeCells.isEmpty()) {
                noWinner = false
                continue
            }
            println("Player: ${playerTurn.playerName} Enter row,col")
            val inputScanner = Scanner(System.`in`)
            val inputStr = inputScanner.nextLine()
            val inputRowCol = inputStr.split(",")
            val inputRow = inputRowCol[0].toInt()
            val inputCol = inputRowCol[1].toInt()

            val isPieceAddedSuccessFully = board.addPieceToBoard(inputRow, inputCol, playerTurn.playingPiece)
            if (!isPieceAddedSuccessFully) {
                println("Incorrect choosen, plz try again")
                playerList.addFirst(playerTurn)
                continue
            }

            playerList.addLast(playerTurn)

            val isThereWinner = isThereWinner(inputRow, inputCol, playerTurn.playingPiece.pieceType)
            if (isThereWinner) {
                board.printBoard()
                return playerTurn.playerName + " wins"
            }
        }

        return "tie"
    }


    private fun isThereWinner(inputRow: Int, inputCol: Int, pieceType: PieceType): Boolean {
        var isRowWinner = true
        var isColWinner = true
        var isDiagonalWinner = true
        var isAntiDiagonalWinner = true

        for (col in 0 until board.boardMatrix.size) {
            if (board.boardMatrix[inputRow][col] == null || board.boardMatrix[inputRow][col]!!.pieceType != pieceType)
                isRowWinner = false
        }

        for (row in 0 until board.boardMatrix.size) {
            if (board.boardMatrix[row][inputCol] == null || board.boardMatrix[row][inputCol]!!.pieceType != pieceType)
                isColWinner = false
        }


        //need to check diagonals
        run {
            var i = 0
            var j = 0
            while (i < board.boardMatrix.size) {
                if (board.boardMatrix[i][j] == null || board.boardMatrix[i][j]!!.pieceType !== pieceType) {
                    isDiagonalWinner = false
                }
                i++
                j++
            }
        }

        //need to check anti-diagonals
        run {
            var i = 0
            var j: Int = board.boardMatrix.size - 1
            while (i < board.boardMatrix.size) {
                if (board.boardMatrix[i][j] == null || board.boardMatrix[i][j]!!.pieceType !== pieceType) {
                    isAntiDiagonalWinner = false
                }
                i++
                j--
            }
        }


        return isRowWinner || isColWinner || isDiagonalWinner || isAntiDiagonalWinner
    }
}