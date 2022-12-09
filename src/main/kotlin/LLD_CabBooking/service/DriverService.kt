package LLD_CabDriver.service

import LLD_CabBooking.model.Driver
import LLD_CabBooking.repository.DriverRepository


class DriverService {
    private val driverRepository = DriverRepository()

    fun addDriver(driver: Driver) {
        driverRepository.addDriver(driver)
    }

    fun removeDriver(driverId: String) {
        driverRepository.removeDriver(driverId)
    }

    fun updateDriver(driver: Driver) {
        driverRepository.updateDriver(driver)

    }

    fun getDriverList(): List<Driver> {
        return driverRepository.getDriverList()
    }


}