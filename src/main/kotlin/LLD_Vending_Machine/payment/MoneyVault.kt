package LLD_Vending_Machine.payment

object MoneyVault {
    val cashStorage = mutableMapOf<Int, Int>()
    private var totalAmt: Int = 0

    fun insertMoney(amount: Int) {
        cashStorage[amount] =
            cashStorage.getOrDefault(amount, 0) + 1
        totalAmt += amount
    }

    fun getTotalAmt(): Int = totalAmt

    fun getChange(changeAmt: Int): Map<Int, Int>? {
        val changeMap = mutableMapOf<Int, Int>()
        var remaining = changeAmt

        val denominations = cashStorage.keys.sortedDescending() // e.g. [100, 50, 20, 10, 5, 1]

        for (denomination in denominations) {
            val availableCount = cashStorage[denomination] ?: 0
            val requiredCount = remaining / denomination

            if (requiredCount > 0) {
                val countToUse = minOf(requiredCount, availableCount)
                if (countToUse > 0) {
                    changeMap[denomination] = countToUse
                    remaining -= denomination * countToUse
                }
            }

            if (remaining == 0) break
        }

        return if (remaining == 0) changeMap else null
    }

    fun dispenseChange(changeMap: Map<Int, Int>, refundAmt: Int) {
        for ((denomination, count) in changeMap) {
            cashStorage[denomination] =
                cashStorage.getOrDefault(denomination, 0) - count
        }
        deduct(refundAmt)
    }

    private fun deduct(refundAmt: Int) {
        totalAmt -= refundAmt
    }

}