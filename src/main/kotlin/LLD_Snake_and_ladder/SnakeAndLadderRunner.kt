package LLD_Snake_and_ladder

import LLD_Snake_and_ladder.service.Game

object SnakeAndLadderRunner {

    @JvmStatic
    fun main(args: Array<String>) {
        Game().startGame()
    }
}