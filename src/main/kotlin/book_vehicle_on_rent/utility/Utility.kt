package book_vehicle_on_rent.utility

import book_vehicle_on_rent.model.location.Location
import book_vehicle_on_rent.repository.VehicleInventoryManager
import book_vehicle_on_rent.model.store.Store
import book_vehicle_on_rent.model.vehicle.Car
import book_vehicle_on_rent.model.vehicle.Vehicle
import book_vehicle_on_rent.enums.VehicleType
import java.util.*
import java.util.concurrent.TimeUnit


object Utility {


    fun storesList(): List<Store> {
        val storeList = mutableListOf<Store>()
        val store1 = Store()
        store1.storeId = 1
        store1.storeLocation =
            Location(
                address = "Bren Edge Waters society",
                city = "Bangalore",
                state = "Karnataka",
                country = "India",
                pinCode = 650035
            )
        store1.vehicleInventoryManager = VehicleInventoryManager(vehicleList = addVehicle() as MutableList<Vehicle>)
        val store2 = Store()
        store2.storeId = 2
        store2.storeLocation =
            Location(
                address = "Trans Yamuna colony",
                city = "Agra",
                state = "Uttar Pradesh",
                country = "India",
                pinCode = 282006
            )
        store2.vehicleInventoryManager = VehicleInventoryManager(vehicleList = addVehicle() as MutableList<Vehicle>)
        val store3 = Store()
        store3.storeId = 3
        store3.storeLocation =
            Location(
                address = "Bellundur",
                city = "Bangalore",
                state = "Karnatka",
                country = "India",
                pinCode = 568711
            )
        store3.vehicleInventoryManager = VehicleInventoryManager(vehicleList = addVehicle() as MutableList<Vehicle>)
        storeList.add(store1)
        storeList.add(store2)
        storeList.add(store3)
        return storeList
    }

    private fun addVehicle(): List<Vehicle> {
        val vehicleList = mutableListOf<Vehicle>()
        val vehicle = Car()
        vehicle.vehicleN0 = 1
        vehicle.vehicleType = VehicleType.CAR
        vehicle.vehicleName = "Sudan"
        vehicle.vehicleCompany = "Hyundai"
        vehicle.noOfSeat = 4

        val vehicle1 = Car()
        vehicle1.vehicleN0 = 2
        vehicle1.vehicleType = VehicleType.CAR
        vehicle1.vehicleName = "Maruti Suzuki"
        vehicle.vehicleCompany = "Honda"
        vehicle1.noOfSeat = 4
        vehicleList.add(vehicle)
        vehicleList.add(vehicle1)
        return vehicleList
    }

    fun getDate(isToday: Boolean = true, dayAfter: Int = 0): Date {
        // get a calendar instance, which defaults to "now"
        val calendar = Calendar.getInstance()
        // get a date to represent "today"
        return if (isToday)
            calendar.time
        else {
            calendar.add(Calendar.DAY_OF_YEAR, dayAfter)
            calendar.time
        }
    }

    fun getDaysBtwDates(dateAfter: Date, dateBefore: Date): Int {
        try {
            val timeDiff = Math.abs(dateAfter.time - dateBefore.time)
            val daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS)
            return daysDiff.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }
}