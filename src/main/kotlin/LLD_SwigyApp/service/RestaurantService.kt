package LLD_SwigyApp.service

import LLD_SwigyApp.model.item.MenuItem
import LLD_SwigyApp.model.restaurant.Restaurant
import LLD_SwigyApp.repository.RestaurantRepository

class RestaurantService {
    private val restaurantRepository = RestaurantRepository()

    fun addRestaurant(restaurant: Restaurant) {
        restaurantRepository.addRestaurant(restaurant)
    }

    fun getRestaurantById(restaurantId: String): Restaurant? {
        return restaurantRepository.getRestaurantById(restaurantId)
    }

    fun getRestaurantByName(restaurantName: String): List<Restaurant>? {
        return restaurantRepository.getRestaurantByName(restaurantName)
    }

    fun getRestaurantByCity(restaurantCity: String): List<Restaurant>? {
        return restaurantRepository.getRestaurantByCity(restaurantCity)
    }

    fun getAllRestaurant(): List<Restaurant> {
        return restaurantRepository.getAllRestaurant()
    }

    fun removeRestaurantById(restaurantId: String) {
        restaurantRepository.removeRestaurantById(restaurantId)
    }

    fun removeMenuItemFromRestaurantById(restaurantId: String, menuItemId: String) {
        restaurantRepository.removeMenuItemFromRestaurantById(restaurantId, menuItemId)
    }

    fun addMenuItemToRestaurantById(restaurantId: String, menuItem: MenuItem) {
        restaurantRepository.addMenuItemToRestaurantById(restaurantId, menuItem)
    }

}