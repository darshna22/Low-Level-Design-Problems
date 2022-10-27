package vending_machine

import vending_machine.product.Item

data class ItemShelf(
    var code: Int,
    var item: Item? = null,
    var isSoldOut: Boolean = false
)