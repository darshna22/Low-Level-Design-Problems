package vending_machine.inventory

import vending_machine.product.Item


class Inventory(private val inventoryItemCount: Int) {
    private var shelfItemList = mutableListOf<ItemShelf>()

    init {
        initializeInitialValue()
    }

    fun getInventory(): ArrayList<ItemShelf> {
        return shelfItemList as ArrayList<ItemShelf>
    }


    private fun initializeInitialValue() {
        var shelfCode = 101
        for (i in 0 until inventoryItemCount) {
            val itemShelf = ItemShelf(code = shelfCode, isSoldOut = false)
            shelfItemList.add(i, itemShelf)
            shelfCode++
        }
    }

    @Throws(Exception::class)
    fun addItem(item: Item?, codeNumber: Int) {
        for (itemShelf in shelfItemList) {
            if (itemShelf.code == codeNumber) {
                if (itemShelf.isSoldOut) {
                    itemShelf.item = item
                    itemShelf.isSoldOut = false
                } else {
                    throw Exception("already item is present, you can not add item here")
                }
            }
        }
    }

    @Throws(Exception::class)
    fun getItem(codeNumber: Int): Item? {
        for (itemShelf in shelfItemList) {
            if (itemShelf.code == codeNumber && itemShelf.item!!.quantity > 0) {
                return itemShelf.item
            } else throw Exception("item already sold out")
        }
        throw Exception("Invalid Code")
    }

    fun updateSoldOutItem(code: Int) {
        for (itemShelf in shelfItemList) {
            if (itemShelf.code == code) {
                if (itemShelf.item!!.quantity > 0)
                    itemShelf.item!!.quantity = itemShelf.item!!.quantity - 1
                else
                    itemShelf.isSoldOut = true
                break
            }
        }
    }


}
