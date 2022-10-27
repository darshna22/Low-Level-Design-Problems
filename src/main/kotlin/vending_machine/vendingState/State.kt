package vending_machine.vendingState

import vending_machine.coin.Coin
import vending_machine.product.Item

interface State {
    fun clickOnInsertCoinButton()

    fun insertCoin(coin: Coin)

    fun selectProduct(itemCode: Int)

    fun clickOnSelectProductButton()

    fun refundFullMoney(): List<Coin>

    fun dispenseProduct(itemCode: Int): Item

    fun getChangeAmt(returnExtraMoney:Int): Int

    fun updateInventoryAfterDispense(item:Item,itemCode: Int)
}


