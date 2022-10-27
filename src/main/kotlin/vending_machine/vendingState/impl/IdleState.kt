package vending_machine.vendingState.impl

import vending_machine.coin.Coin
import vending_machine.product.Item
import vending_machine.vendingMachine.VendingMachine
import vending_machine.vendingState.State


class IdleState(private val machine: VendingMachine): State {

    override fun clickOnInsertCoinButton() {
        machine.setVendingMachineState(HasMoneyState(machine))
    }

    override fun insertCoin( coin: Coin) {
        throw  Exception("you can not insert Coin in idle state")
    }

    @Throws(java.lang.Exception::class)
    override fun clickOnSelectProductButton() {
        throw  Exception("first you need to click on insert coin button")
    }


    override fun selectProduct( itemCode: Int) {
        throw Exception("you can not choose Product in idle state")
    }


    override fun refundFullMoney(): List<Coin> {
        throw Exception("you can not get refunded in idle state")
    }

    override fun dispenseProduct( itemCode: Int): Item {
        throw  Exception("product can not be dispensed idle state")
    }

    override fun getChangeAmt(returnExtraMoney:Int): Int {
        throw  Exception("you can not get change in idle state")
    }

    override fun updateInventoryAfterDispense(item:Item ,itemCode: Int) {
        machine.getInventory().addItem(item ,itemCode)
    }
}