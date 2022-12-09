package LLD_CabBooking.repository

import LLD_CabBooking.model.Driver

class DriverRepository {
    //key should be riderId
    private var driverMap = mutableMapOf<String, Driver>()

    fun addDriver(driver: Driver) {
        if (!driverMap.containsKey(driver.driverId)) {
            driverMap[driver.driverId] = driver
        }
    }

    fun removeDriver(riderId: String) {
        driverMap.remove(riderId)
    }

    fun updateDriver(driver: Driver) {
        if (driverMap.containsKey(driver.driverId)) {
            driverMap[driver.driverId] = driver
        }
    }

    fun getDriverList(): List<Driver> {
        return driverMap.values.toList()
    }

}