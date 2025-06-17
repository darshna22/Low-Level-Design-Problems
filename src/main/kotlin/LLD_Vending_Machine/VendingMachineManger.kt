package LLD_Vending_Machine

import LLD_Vending_Machine.enums.PaymentMode
import LLD_Vending_Machine.payment.strategy.CashPaymentStrategy
import LLD_Vending_Machine.product.Product
import LLD_Vending_Machine.product.ProductInventory

fun main() {
    val vendingMachine = VendingMachine(ProductInventory(), TransactionManager(CashPaymentStrategy))

    // Add Products
    vendingMachine.addProduct(Product("1", "Coke", 50, 10))
    vendingMachine.addProduct(Product("2", "Pepsi", 50, 10))
    vendingMachine.addProduct(Product("3", "Water", 30, 10))
    vendingMachine.addProduct(Product("4", "Soda", 40, 10))
    vendingMachine.addProduct(Product("5", "Dahi", 55, 10))
    vendingMachine.addProduct(Product("6", "Dosa Batter", 55, 10))
    vendingMachine.addProduct(Product("7", "Ice Cream", 65, 10))
    vendingMachine.addProduct(Product("8", "Noodles", 60, 10))

    println("=========== Welcome to the Vending Machine ===========")
    while (true) {
        println("Please select payment mode from below (or type 'exit to quit): \n 1 for Cash\n 2 for Card \n 3 for UPI \n ")

        val paymentMode = readln()
        if (paymentMode.lowercase() == "exit") {
            break
        }
        val selectedPaymentMode = when (paymentMode.toIntOrNull()) {
            1 -> PaymentMode.CASH
            2 -> PaymentMode.CARD
            3 -> PaymentMode.UPI
            else -> {
                println("Invalid mode, defaulting to CASH")
                PaymentMode.CASH
            }
        }
        vendingMachine.changePaymentStrategy(selectedPaymentMode)
        println("Your payment mode is: ${selectedPaymentMode.name}")

        //Display vending machine screen
        vendingMachine.displayScreen()


        println("Please enter product code (or type 'exit to quit):")
        val selectedProductCode = readln()
        if (selectedProductCode.lowercase() == "exit") {
            break
        }

        try {
            vendingMachine.selectProduct(selectedProductCode)
        } catch (e: Exception) {
            println("Error selecting product: ${e.message}")
        }

        println("Please insert/pay money (type 'done' to finish inserting")
        while (true) {
            val input = readln()
            if (input.lowercase() == "done") {
                break
            }

            val amt = input.toIntOrNull()
            try {
                if (amt != null) {
                    vendingMachine.addMoney(amt)
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }

        try {
            vendingMachine.dispense()
        } catch (e: Exception) {
            println("Transaction failed: ${e.message}")
        }

        println("=====================================================\n")
    }
    println("Thank you for using the Vending Machine!")
}
