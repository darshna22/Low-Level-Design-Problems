package LLD_CabBooking.utility

import LLD_CabBooking.model.Driver
import LLD_CabBooking.model.Location
import LLD_CabBooking.model.Rider
import LLD_CabBooking.model.cab.Car
import LLD_CabBooking.model.cab.SedanCar
import LLD_CabBooking.repository.CabRepository
import java.util.UUID

object MyUtility {
    fun createCabList(): List<Car> {
        val cabMapList = mutableListOf<Car>()

        //Add Cab 1.
        val cab1 = SedanCar(
            cabId = UUID.randomUUID().toString(),
            cabBasePrice = 50.00,
            cabFair = 0.0,
            cabLocation = Location(20, 40),
        )

        //Add Cab 2.
        val cab2 = SedanCar(
            cabId = UUID.randomUUID().toString(),
            cabBasePrice = 40.00,
            cabFair = 0.0,
            cabLocation = Location(40, 60),
        )
        cabMapList.add(cab1)
        cabMapList.add(cab2)
        return cabMapList
    }

    fun getDriver(): Driver {
        return Driver(UUID.randomUUID().toString(), driverName = "Santosh")

    }

    fun getRider(): Rider {
        return Rider(
            riderId = UUID.randomUUID().toString(),
            riderName = "Darshna",
            riderSourceLoc = Location(20, 40),
            riderDestinationLoc = Location(40, 60)
        )
    }

}