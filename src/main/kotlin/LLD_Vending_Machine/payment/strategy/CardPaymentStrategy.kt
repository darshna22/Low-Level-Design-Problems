package LLD_Vending_Machine.payment.strategy

import LLD_Vending_Machine.payment.MoneyVault
import LLD_Vending_Machine.product.Product

object CardPaymentStrategy : PaymentStrategy {
    private var totalInsertAmt = 0
    private var totalCardAmt = 0

    override fun insertMoney(amount: Int) {
        MoneyVault.cashStorage[amount] =
            MoneyVault.cashStorage.getOrDefault(amount, 0) + 1
        totalCardAmt += amount
        totalCardAmt + amount
    }

    override fun getInsertedAmount(): Int = totalInsertAmt

    override fun getChange(product: Product): Map<Int, Int>? = null

    override fun dispenseChange(changeMap: Map<Int, Int>) {
    }

    override fun reset() {
        totalInsertAmt = 0
    }
}
