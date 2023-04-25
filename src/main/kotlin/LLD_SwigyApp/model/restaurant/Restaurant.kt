package LLD_SwigyApp.model.restaurant

import LLD_SwigyApp.model.address.Address
import LLD_SwigyApp.model.item.MenuItem

data class Restaurant(
    val restaurantId: String,
    val restaurantName: String,
    val restaurantAdd: Address,
    var menuItemList: Set<MenuItem>
)
