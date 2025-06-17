package LLD_Vending_Machine

import LLD_Vending_Machine.enums.PaymentMode
import LLD_Vending_Machine.payment.strategy.CardPaymentStrategy
import LLD_Vending_Machine.payment.strategy.CashPaymentStrategy
import LLD_Vending_Machine.payment.strategy.PaymentStrategy
import LLD_Vending_Machine.payment.strategy.UPIPaymentStrategy
import LLD_Vending_Machine.product.Product

class TransactionManager(
    private var paymentStrategy: PaymentStrategy
) {
    private var selectedProduct: Product? = null

    fun changePaymentStrategy(paymentMode: PaymentMode) {
        val selectedPaymentMode = when (paymentMode) {
            PaymentMode.CASH -> CashPaymentStrategy
            PaymentMode.CARD -> CardPaymentStrategy
            PaymentMode.UPI -> UPIPaymentStrategy
        }
        paymentStrategy.reset()
        this.paymentStrategy = selectedPaymentMode
    }

    fun selectProduct(product: Product) {
        selectedProduct = product
    }

    fun getSelectedProduct(): Product? = selectedProduct

    fun clear() {
        selectedProduct = null
        paymentStrategy.reset()
    }

    fun insertMoney(amount: Int) {
        paymentStrategy.insertMoney(amount)
    }

    fun getInsertedAmount(): Int = paymentStrategy.getInsertedAmount()

    fun resetPayment() = paymentStrategy.reset()

    fun getChange(product: Product): Map<Int, Int>? = paymentStrategy.getChange(product)

    fun dispenseChange(changeMap: Map<Int, Int>) = paymentStrategy.dispenseChange(changeMap)
}

