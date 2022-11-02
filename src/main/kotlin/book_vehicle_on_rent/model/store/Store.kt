package book_vehicle_on_rent.model.store

import book_vehicle_on_rent.model.location.Location
import book_vehicle_on_rent.repository.VehicleInventoryManager

class Store {
    var storeId: Int? = null
    var vehicleInventoryManager: VehicleInventoryManager? = null
    var storeLocation: Location? = null

}