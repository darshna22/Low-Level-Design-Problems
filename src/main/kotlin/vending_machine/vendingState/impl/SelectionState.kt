package vending_machine.vendingState.impl

import vending_machine.coin.Coin
import vending_machine.product.Item
import vending_machine.vendingMachine.VendingMachine
import vending_machine.vendingState.State

class SelectionState(private val machine: VendingMachine) : State {
    init {
        println("Currently Vending machine is in SelectionState")
    }

    override fun clickOnInsertCoinButton() {
        throw Exception("you can not click on insert Coin btn in Selection state")
    }

    override fun insertCoin(coin: Coin) {
        throw Exception("you can not insert Coin in Selection state")

    }

    override fun clickOnSelectProductButton() {
        return
    }

    override fun selectProduct(itemCode: Int) {
        val item = machine.getInventory().getItem(itemCode)
        val coinList = machine.getCoinList()
        //1. calculate paid price
        var totalPricePaid = 0.0
        for (coin in coinList) {
            totalPricePaid += coin.value
        }
        //2. Check paid price with item price if less then return paid money with msg
        if (totalPricePaid < (item?.itemPrice ?: 0)) {
            println("Insufficient Amount, Product you selected is for price: " + item?.itemPrice + " and you paid: " + totalPricePaid)
            refundFullMoney()
            throw Exception("insufficient amount")
        } else {
            if (totalPricePaid > (item?.itemPrice ?: 0)) {
                getChangeAmt((totalPricePaid - item!!.itemPrice).toInt())
            }
            machine.setVendingMachineState(DispenseState(machine))
        }
    }


    override fun refundFullMoney(): List<Coin> {
        println("Returned the full amount back in the Coin Dispense Tray");
        machine.setVendingMachineState(IdleState(machine))

        return machine.getCoinList()

    }

    override fun dispenseProduct(itemCode: Int): Item {
        throw Exception("product can not be dispensed in hasMoney state")
    }

    override fun getChangeAmt(returnExtraMoney: Int): Int {
        println("Extra money $returnExtraMoney has been returned")
        return returnExtraMoney
    }

    override fun updateInventoryAfterDispense(
        item: Item,
        itemCode: Int
    ) {
        throw Exception("you can not update inventory in hasMoney  state")

    }
}