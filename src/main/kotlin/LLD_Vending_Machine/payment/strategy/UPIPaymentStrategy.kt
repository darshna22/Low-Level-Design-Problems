package LLD_Vending_Machine.payment.strategy

import LLD_Vending_Machine.payment.MoneyVault
import LLD_Vending_Machine.product.Product

object UPIPaymentStrategy : PaymentStrategy {
    private var totalInsertAmt = 0
    private var totalUPIAmt = 0

    override fun insertMoney(amount: Int) {
        MoneyVault.cashStorage[amount] =
            MoneyVault.cashStorage.getOrDefault(amount, 0) + 1
        totalUPIAmt += amount
        totalUPIAmt + amount
    }

    override fun getInsertedAmount(): Int = totalInsertAmt

    override fun getChange(product: Product): Map<Int, Int>? = null

    override fun dispenseChange(changeMap: Map<Int, Int>) {
    }

    override fun reset() {
        totalInsertAmt = 0
    }
}
