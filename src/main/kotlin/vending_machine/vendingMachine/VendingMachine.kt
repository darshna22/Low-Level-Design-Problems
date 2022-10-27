package vending_machine.vendingMachine

import vending_machine.Inventory
import vending_machine.coin.Coin
import vending_machine.vendingState.State
import vending_machine.vendingState.impl.IdleState


class VendingMachine(private val inventoryItemCount: Int) {
    private var vendingMachineState: State
    private var inventory: Inventory = Inventory(inventoryItemCount)
    private var coinList: MutableList<Coin>

    init {
        vendingMachineState = IdleState(this)
        coinList = mutableListOf<Coin>()
    }

    fun setVendingMachineState(vendingMachineState: State) {
        this.vendingMachineState = vendingMachineState
    }

    fun getVendingMachineState() = vendingMachineState

    fun getCoinList() = coinList

    fun getInventory() = inventory


}
