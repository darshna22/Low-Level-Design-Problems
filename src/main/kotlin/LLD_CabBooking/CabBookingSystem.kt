package LLD_CabBooking

import LLD_CabBooking.enums.CabStatus
import LLD_CabBooking.enums.DriverStatus
import LLD_CabBooking.model.Booking
import LLD_CabBooking.model.Driver
import LLD_CabBooking.model.Location
import LLD_CabBooking.model.Rider
import LLD_CabBooking.model.cab.Car
import LLD_CabBooking.service.BookingService
import LLD_CabCar.service.CabService
import LLD_CabDriver.service.DriverService
import LLD_CabRider.service.RiderService
import java.util.*

class CabBookingSystem {
    private val bookingService = BookingService()
    private val driverService = DriverService()
    private val riderService = RiderService()
    private val cabService = CabService()

    fun registerDriver(driver: Driver) {
        driverService.addDriver(driver)
    }

    fun registerRider(rider: Rider) {
        riderService.addRider(rider)
    }

    fun registerCab(car: Car) {
        cabService.addCar(car)
        assignCabToDriver(car)
    }

    private fun assignCabToDriver(car: Car) {
        val driverList = driverService.getDriverList()
        val driverFilteredList = driverList.filter { driver: Driver -> driver.car == null }
        if (driverFilteredList.isNotEmpty()) {
            val driver = driverFilteredList[0]
            driver.car = car
            driverService.updateDriver(driver)
        }
    }


    fun bookCab(rider: Rider): Driver {
        val riderList = riderService.getRiderList()
        if (!riderList.contains(rider))
            riderService.addRider(rider)
        val listOfDrivers = driverService.getDriverList()
        val filteredList = listOfDrivers.filter { it.driverStatus == DriverStatus.FREE }
        val riderTravelDistance = calculateDistance(rider.riderSourceLoc, rider.riderDestinationLoc)

        var nearestDriver: Driver? = null
        var minCabDistance = Double.MAX_VALUE
        //find min distance available cab
        for (driver in filteredList) {
            val cabDistance = calculateDistance(rider.riderSourceLoc, driver.car!!.cabLocation)
            if (cabDistance < minCabDistance) {
                minCabDistance = cabDistance
                nearestDriver = driver
            }
        }
        //book cab for rider
        require(nearestDriver != null) {
            "Car not found"
        }
        //check driver for car
        nearestDriver.driverStatus = DriverStatus.BOOKED
        val booking = Booking(
            bookingId = UUID.randomUUID().toString(),
            rider = rider,
            driver = nearestDriver,
            0.0,
            System.currentTimeMillis(),
            0.0.toLong()
        )
        bookingService.addBooking(rider.riderId, booking)
        return nearestDriver
    }

    private fun calculateDistance(sourceLocation: Location, destination: Location): Double =
        Math.sqrt(
            Math.pow(
                sourceLocation.xCoordinate.toDouble() - destination.xCoordinate.toDouble(),
                2.0
            ) + Math.pow(sourceLocation.yCoordinate.toDouble() - destination.yCoordinate.toDouble(), 2.0)
        )


    fun fetchRiderHistory(riderId: String): List<Booking>? {
        return bookingService.getRiderHistory(riderId)
    }

    private fun updateCabLocation(driver: Driver) {
        driver.driverStatus = DriverStatus.FREE
        driver.car!!.cabStatus = CabStatus.FREE
        driverService.updateDriver(driver)
    }

    //finish rider ride for car
    fun completeRide(driver: Driver): String {
        //generate receipt if required
        updateCabLocation(driver)
        return "Ride has been completed"
    }

    fun fetchDiverHistory(driverId: String): List<Booking> {
        val bookingList = bookingService.getBookingList()
        return bookingList.filter { booking -> booking.driver.driverId == driverId }
    }

    fun fetchCabHistory(cabId: String): List<Booking> {
        val bookingList = bookingService.getBookingList()
        return bookingList.filter { booking -> booking.driver.car!!.cabId == cabId }
    }

}