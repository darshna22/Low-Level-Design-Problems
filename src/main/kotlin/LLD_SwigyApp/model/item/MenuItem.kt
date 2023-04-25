package LLD_SwigyApp.model.item

import LLD_SwigyApp.enums.FoodType
import LLD_SwigyApp.enums.MenuItemType

data class MenuItem(
    val itemId: String,
    val itemName: String,
    val itemPrice: Double,
    val foodType: FoodType,
    val menuItemType:MenuItemType,
    var itemQuantity:Int
)