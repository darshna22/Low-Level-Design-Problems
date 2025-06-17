package LLD_Vending_Machine.payment.strategy

import LLD_Vending_Machine.product.Product

interface PaymentStrategy {
    fun insertMoney(amount: Int)

    fun getInsertedAmount(): Int

    fun getChange(product: Product): Map<Int, Int>?

    fun dispenseChange(changeMap: Map<Int, Int>)
    fun reset()
}