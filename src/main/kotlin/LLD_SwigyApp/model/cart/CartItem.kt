package LLD_SwigyApp.model.cart

import LLD_SwigyApp.model.address.Address
import LLD_SwigyApp.model.item.MenuItem

data class CartItem(
    var restaurantId: String,
    var restaurantName: String,
    var restaurantAdd: Address,
    var menuItemList: List<MenuItem>
)