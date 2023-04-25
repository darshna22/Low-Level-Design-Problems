package LLD_SwigyApp.repository

import LLD_SwigyApp.model.cart.CartItem
import LLD_SwigyApp.model.item.MenuItem

class CartRepository {

    private var cartItemMap: MutableMap<String, CartItem> = mutableMapOf<String, CartItem>()

    fun addProduct(restaurantId: String, mCartItem: CartItem):Boolean {
        if (!cartItemMap.containsKey(restaurantId)) {
            cartItemMap[restaurantId] = mCartItem
            return true
        }
      return false
    }

    fun updateQuantity(restaurantId: String, mMenuItem: MenuItem, isAdd: Boolean) {
        val cartItem = cartItemMap[restaurantId]
        val mMenuItemList = cartItem!!.menuItemList as MutableList
        if (mMenuItemList.size == 1 && mMenuItemList[0].itemQuantity == 1 && !isAdd) {
            cartItemMap.remove(restaurantId)
            return
        } else if (mMenuItemList.size == 1 && mMenuItemList[0].itemQuantity == 1 && isAdd) {
            val menuItem = mMenuItemList[0]
            menuItem.itemQuantity = menuItem.itemQuantity + 1
            mMenuItemList.add(0, menuItem)
        } else {
            var i = 0
            for (menuItem in mMenuItemList) {
                if (menuItem.itemId == mMenuItem.itemId && isAdd) {
                    menuItem.itemQuantity = menuItem.itemQuantity + 1
                    mMenuItemList.add(i, menuItem)
                    break
                } else if (menuItem.itemId == mMenuItem.itemId) {
                    menuItem.itemQuantity = menuItem.itemQuantity - 1
                    mMenuItemList.add(i, menuItem)
                    break
                }
                i++
            }
            cartItem.menuItemList = mMenuItemList
            cartItemMap[restaurantId] = cartItem
        }

    }


    fun getAllCartItem(): List<CartItem> {
        return cartItemMap.values as List<CartItem>
    }

}