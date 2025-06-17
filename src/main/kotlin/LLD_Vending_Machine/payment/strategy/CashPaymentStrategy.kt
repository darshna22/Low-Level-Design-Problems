package LLD_Vending_Machine.payment.strategy

import LLD_Vending_Machine.product.Product
import LLD_Vending_Machine.enums.CoinNoteType
import LLD_Vending_Machine.payment.MoneyVault

object CashPaymentStrategy : PaymentStrategy {
    private var totalInsertAmt = 0
    private var changeAmt = 0


    private fun isDenominationAvailable(amount: Int): Boolean {
        return CoinNoteType.values().any { it.value == amount }
    }

    override fun insertMoney(amount: Int) {

        if (isDenominationAvailable(amount)) {
            MoneyVault.cashStorage[amount] =
                MoneyVault.cashStorage.getOrDefault(amount, 0) + 1
            totalInsertAmt += amount
        } else {
            throw Exception("Invalid denomination please select denominator type of 1,5,10,20,50,100,500")
        }
    }

    override fun getInsertedAmount(): Int = totalInsertAmt

    override fun getChange(product: Product): Map<Int, Int>? {
        if (totalInsertAmt < product.price) return null
        changeAmt = totalInsertAmt - product.price
        return MoneyVault.getChange(changeAmt)
    }

    override fun dispenseChange(changeMap: Map<Int, Int>) {
        MoneyVault.dispenseChange(changeMap, changeAmt)
        println("Enter Amt: $totalInsertAmt and Amt refunded is: $changeMap")
    }

    override fun reset() {
        totalInsertAmt = 0
        changeAmt = 0
    }
}
