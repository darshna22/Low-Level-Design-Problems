package book_vehicle_on_rent.repository

import book_vehicle_on_rent.model.vehicle.Vehicle

class VehicleInventoryManager(val vehicleList: MutableList<Vehicle>) {

    fun addVehicleToList(vehicle: Vehicle) {
        vehicleList.add(vehicle)
    }

    fun removeVehicleFromList(vehicle: Vehicle) {
        vehicleList.remove(vehicle)
    }
}