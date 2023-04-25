package LLD_SwigyApp.repository

import LLD_SwigyApp.model.item.MenuItem
import LLD_SwigyApp.model.restaurant.Restaurant


class RestaurantRepository {
    private val menuItemRepository = MenuItemRepository()
    private var restaurantMap: MutableMap<String, Restaurant> = mutableMapOf<String, Restaurant>()
    private var restaurantIdsByName: MutableMap<String, List<Restaurant>> = mutableMapOf<String, List<Restaurant>>()
    private var restaurantIdsByCity: MutableMap<String, List<Restaurant>> = mutableMapOf<String, List<Restaurant>>()

    fun addRestaurant(restaurant: Restaurant) {
        if (restaurantMap.containsKey(restaurant.restaurantId)) {
            throw Exception("This restaurant already added")
        }
        restaurantMap[restaurant.restaurantId] = restaurant
        //add all menu items of restaurant to menu item map
        menuItemRepository.addMenuItemsToRestaurant(restaurant.restaurantId, restaurant.menuItemList)

        //Save restaurant by name
        val restaurantNameList: MutableList<Restaurant> =
            restaurantIdsByName.getOrDefault(
                restaurant.restaurantName,
                mutableListOf<Restaurant>()
            ) as MutableList<Restaurant>
        restaurantNameList.add(restaurant)
        restaurantIdsByName[restaurant.restaurantName] = restaurantNameList


        //Save restaurant by city
        val restaurantCityList: MutableList<Restaurant> =
            restaurantIdsByCity.getOrDefault(
                restaurant.restaurantAdd.cityName,
                mutableListOf<Restaurant>()
            ) as MutableList<Restaurant>
        restaurantNameList.add(restaurant)
        restaurantIdsByCity[restaurant.restaurantAdd.cityName] = restaurantCityList
    }

    fun removeRestaurantById(restaurantId: String) {
        restaurantMap.remove(restaurantId)
    }

    fun removeMenuItemFromRestaurantById(restaurantId: String, menuItemId: String) {
        menuItemRepository.removeMenuItemFromRestaurantById(restaurantId, menuItemId)
        updateMap(restaurantId)
    }

    fun addMenuItemToRestaurantById(restaurantId: String, menuItem: MenuItem) {
        menuItemRepository.addMenuItemToRestaurantById(restaurantId, menuItem)
        updateMap(restaurantId)
    }

    private fun updateMap(restaurantId: String) {
        restaurantMap[restaurantId]!!.menuItemList = menuItemRepository.getAllMenuItemsOfRestaurant(restaurantId)!!
    }

    fun getRestaurantById(restaurantId: String): Restaurant? {
        if (!restaurantMap.containsKey(restaurantId))
            throw Exception("Restaurant does not exist")
        return restaurantMap[restaurantId]
    }

    fun getRestaurantByName(restaurantName: String): List<Restaurant>? {
        if (!restaurantIdsByName.containsKey(restaurantName))
            throw Exception("Restaurant does not exist")
        return restaurantIdsByName[restaurantName]
    }

    fun getRestaurantByCity(restaurantCity: String): List<Restaurant>? {
        if (!restaurantIdsByCity.containsKey(restaurantCity))
            throw Exception("Restaurant does not exist")
        return restaurantIdsByCity[restaurantCity]
    }

    fun getAllRestaurant(): List<Restaurant> {
        return restaurantMap.values as List<Restaurant>
    }


}
