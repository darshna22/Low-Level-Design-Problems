package LLD_SwigyApp.repository

import LLD_SwigyApp.model.item.MenuItem


class MenuItemRepository {
    private var menuItemMap: MutableMap<String, Set<MenuItem>> = mutableMapOf<String, Set<MenuItem>>()

    fun addMenuItemToRestaurantById(restaurantId: String, menuItem: MenuItem) {

        val menuItemSet: MutableSet<MenuItem> =
            menuItemMap.getOrDefault(
                restaurantId,
                mutableSetOf<MenuItem>()
            ) as MutableSet<MenuItem>

        if (menuItemSet.contains(menuItem)) {
            throw Exception("This menuItem already added")
        }
        menuItemSet.add(menuItem)
        menuItemMap[restaurantId] = menuItemSet

    }

    fun addMenuItemsToRestaurant(restaurantId: String, menuItemList: Set<MenuItem>) {

        val menuItemSet: MutableSet<MenuItem> =
            menuItemMap.getOrDefault(
                restaurantId,
                mutableSetOf<MenuItem>()
            ) as MutableSet<MenuItem>


        menuItemSet.addAll(menuItemList)
        menuItemMap[restaurantId] = menuItemSet

    }

    fun removeMenuItemFromRestaurantById(restaurantId: String, menuItemId: String) {
        val menuItemSet: MutableSet<MenuItem> =
            menuItemMap.getOrDefault(
                restaurantId,
                mutableSetOf<MenuItem>()
            ) as MutableSet<MenuItem>

        for (menuItem in menuItemSet) {
            if (menuItem.itemId == menuItemId) {
                menuItemSet.remove(menuItem)
                break
            }
        }
        //throw Exception("This menuItem not exists")
    }


    fun getAllMenuItemsOfRestaurant(restaurantId: String): Set<MenuItem>? {
        return menuItemMap[restaurantId]
    }

}
