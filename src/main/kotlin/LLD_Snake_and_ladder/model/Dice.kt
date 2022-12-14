package LLD_Snake_and_ladder.model

import java.util.concurrent.ThreadLocalRandom

class Dice(
    var diceCount: Int,
    var min: Int = 1,
    var max: Int = 6
) {
    fun rollDice(): Int {
        var totalSum = 0
        var diceUsed = 0
        while (diceUsed < diceCount) {
            totalSum += ThreadLocalRandom.current().nextInt(min, max + 1)
            diceUsed++;
        }
        return totalSum
    }
}
