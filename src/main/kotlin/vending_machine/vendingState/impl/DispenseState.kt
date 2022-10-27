package vending_machine.vendingState.impl

import vending_machine.coin.Coin
import vending_machine.product.Item
import vending_machine.vendingMachine.VendingMachine
import vending_machine.vendingState.State

class DispenseState(private val machine: VendingMachine) : State {

    init {
        println("Currently Vending machine is in DispenseState")
    }

    override fun clickOnInsertCoinButton() {
        throw Exception("you can not click on insert Coin btn in Dispense state")

    }

    override fun insertCoin(coin: Coin) {
        throw Exception("you can not insert Coin in Dispense state")

    }

    override fun clickOnSelectProductButton() {
        throw Exception("you can not select product btn in Dispense state")
    }

    override fun selectProduct(itemCode: Int) {
        throw Exception("you can not select product in Dispense state")
    }

    override fun refundFullMoney(): List<Coin> {
        throw Exception("you can not get return money in Dispense state")
    }


    override fun getChangeAmt(returnExtraMoney: Int): Int {
        throw Exception("you can not get change in Dispense state")
    }

    override fun dispenseProduct(itemCode: Int): Item {
        println("Product has been dispensed")
        val item = machine.getInventory().getItem(itemCode)
        machine.setVendingMachineState(IdleState(machine))
        machine.getInventory().updateSoldOutItem(itemCode)
        return item!!
    }


    override fun updateInventoryAfterDispense(item: Item, itemCode: Int) {
        TODO("Not yet implemented")
    }
}