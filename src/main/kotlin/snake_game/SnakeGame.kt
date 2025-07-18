package snake_game

import kotlin.system.exitProcess

fun main() {
    println("-----------Let's Start Snake Moving Game-------------")
    println("-----------To play this game, you have to move in up, down, left, or right directions. Avoid returning to the previous position and crossing the screen boundaries.-------------")

    val shapeWidth = 100
    val shapeHeight = 100
    var x = shapeWidth / 2
    var y = shapeHeight / 2
    var previousMoveX = x
    var previousMoveY = y
    var snakeSize = 3
    var move = 0

    while (true) {
        println("Please select one of the directions: up -> 1, down -> 2, left -> 3, right -> 4")
        val direction = readlnOrNull()?.toIntOrNull()

        when (direction) {
            1 -> y -= 10 // Move up
            2 -> y += 10 // Move down
            3 -> x -= 10 // Move left
            4 -> x += 10 // Move right
            else -> {
                println("Invalid direction. Please try again.")
                continue
            }
        }

        // Check boundaries and previous position
        if (x >= shapeWidth || y >= shapeHeight || x < 0 || y < 0 || (previousMoveX == x && previousMoveY == y)) {
            println("Game Over! Snake size is $1")
            exitProcess(0)
        }

        // Update previous position
        previousMoveX = x
        previousMoveY = y

        // Increment move counter
        move++
        if (move == 5) {
            snakeSize++
            move = 0
        }
    }


}