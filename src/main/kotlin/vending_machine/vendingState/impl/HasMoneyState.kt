package vending_machine.vendingState.impl

import vending_machine.coin.Coin
import vending_machine.product.Item
import vending_machine.vendingMachine.VendingMachine
import vending_machine.vendingState.State

class HasMoneyState(private val machine: VendingMachine) : State {


    override fun clickOnInsertCoinButton() {
        println("Currently Vending machine is in HasMoneyState")
    }

    override fun insertCoin(coin: Coin) {
        println("Accepted the coin")
        machine.getCoinList().add(coin)

    }

    override fun clickOnSelectProductButton() {
        machine.setVendingMachineState(SelectionState(machine))

    }

    override fun selectProduct(itemCode: Int) {
        throw Exception("you need to click on start product selection button first")
    }


    override fun refundFullMoney(): List<Coin> {
        println("Returned the full amount back in the Coin Dispense Tray")
        machine.setVendingMachineState(IdleState(machine))
        return machine.getCoinList()

    }

    override fun dispenseProduct(itemCode: Int): Item {
        throw Exception("product can not be dispensed in hasMoney state")
    }

    override fun getChangeAmt(returnExtraMoney:Int): Int {
        throw Exception("you can not get change in hasMoney state")
    }

    override fun updateInventoryAfterDispense(
        item: Item,
        itemCode: Int
    ) {
        throw Exception("you can not update inventory in hasMoney  state")

    }

}
