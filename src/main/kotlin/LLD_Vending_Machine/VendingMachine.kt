package LLD_Vending_Machine

import LLD_Vending_Machine.enums.PaymentMode
import LLD_Vending_Machine.payment.strategy.PaymentStrategy
import LLD_Vending_Machine.product.Product
import LLD_Vending_Machine.product.ProductInventory

class VendingMachine(
    private val productInventory: ProductInventory,
    private val transactionManager: TransactionManager
) {
    fun changePaymentStrategy(paymentMode: PaymentMode) {
        transactionManager.changePaymentStrategy(paymentMode)
    }

    fun addProduct(product: Product) {
        productInventory.addProduct(product)
    }

    fun displayScreen() {
        productInventory.showAllProducts()
    }

    fun selectProduct(code: String) {
        val product = productInventory.getProduct(code)
            ?: throw IllegalArgumentException("Invalid product code.")

        if (product.quantity <= 0) {
            throw IllegalStateException("Product is out of stock")
        }

        transactionManager.selectProduct(product)
        println("Product '${product.name}' selected. Price: ₹${product.price}")
    }

    fun addMoney(amount: Int) {
        transactionManager.insertMoney(amount)
    }

    fun dispense() {
        val product = transactionManager.getSelectedProduct()
            ?: throw IllegalStateException("No product selected")

        val inserted = transactionManager.getInsertedAmount()

        if (inserted < product.price) {
            println("Insufficient funds. Please add ₹${product.price - inserted} more.")
            return
        }

        val change = inserted - product.price
        if (change > 0) {
            val changeMap = transactionManager.getChange(product)
            if (changeMap == null) {
                println("Cannot return change ₹$change. Transaction cancelled.")
                return
            }
            transactionManager.dispenseChange(changeMap)
        }

        productInventory.updateProductQuantity(product.code)
        println("Dispensing ${product.name}")
        transactionManager.clear()
    }

    fun cancelTransaction() {
        val refunded = transactionManager.getInsertedAmount()
        println("Transaction cancelled. Refunding ₹$refunded")
        transactionManager.clear()
    }
}