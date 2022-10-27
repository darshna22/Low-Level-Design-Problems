package vending_machine

import vending_machine.coin.Coin
import vending_machine.product.Item
import vending_machine.product.ItemType
import vending_machine.vendingMachine.VendingMachine

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val vendingMachine = VendingMachine(10)

        println("|")
        println("filling up the inventory")
        println("|")
        fillUpInventory(vendingMachine.getInventory())
        displayInventory(vendingMachine.getInventory()
        )
        println("|")
        println("clicking on InsertCoinButton")
        println("|")

        var vendingMachineState=vendingMachine.getVendingMachineState()
        vendingMachineState.clickOnInsertCoinButton()

        vendingMachineState = vendingMachine.getVendingMachineState()
        vendingMachineState.insertCoin(Coin.FIVE)
        vendingMachineState.insertCoin(Coin.TEN)

        println("|")
        println("clicking on SelectProductButton")
        println("|")
        vendingMachineState.clickOnSelectProductButton()

        vendingMachineState = vendingMachine.getVendingMachineState()
        vendingMachineState.selectProduct(101)
        println("|")
        println("Dispense product")
        println("|")

        vendingMachineState = vendingMachine.getVendingMachineState()

        vendingMachineState.dispenseProduct(101)
        displayInventory(vendingMachine.getInventory())




    }

    private fun fillUpInventory(inventory: Inventory) {
        val inventory = inventory.getInventory()
        for (index in 0 until inventory.size) {
            val itemShelf= inventory[index]
            if (index in 0..2) {
                val item = Item(itemPrice = 12,itemType = ItemType.COKE, quantity = 2)
                itemShelf.isSoldOut=false
                itemShelf.item=item
            }
            if (index in 3..5) {
                val item = Item(itemPrice = 15,itemType = ItemType.SODA, quantity = 1)
                itemShelf.isSoldOut=false
                itemShelf.item=item
            }
            if (index in 6..8) {
                val item = Item(itemPrice = 20,itemType = ItemType.PEPSI, quantity = 3)
                itemShelf.isSoldOut=false
                itemShelf.item=item
            }
        }
    }

    private fun displayInventory(inventory: Inventory) {
        val inventoryShelf = inventory.getInventory()
        for (item in inventoryShelf) {
            println(
                "CodeNumber: " + item.code +
                        " Item: " + (item.item?.itemType ?: "") +
                        " Price: " + (item.item?.itemPrice ?: 0) +
                        " isAvailable: " + item.isSoldOut+
                        " Quantity: " + item.item?.quantity
            )
        }

    }
}