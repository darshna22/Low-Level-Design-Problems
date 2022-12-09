package LLD_CabBooking

import LLD_CabBooking.utility.MyUtility
import LLD_CabBooking.utility.MyUtility.getDriver
import LLD_CabBooking.utility.MyUtility.getRider
import deck_of_cards.model.SimpleDeckOfCardImpl
import deck_of_cards.model.Card
import kotlin.jvm.JvmStatic

object CabBookingRunner {
    @JvmStatic
    fun main(args: Array<String>) {
        val carList = MyUtility.createCabList()
        val cabBookingSystem = CabBookingSystem()

        //1. register driver
        val regiDriver = getDriver()
        cabBookingSystem.registerDriver(regiDriver)

        //2. register car in booking system
        for (car in carList)
            cabBookingSystem.registerCab(car)


        //3. register rider
        val rider = getRider()
        cabBookingSystem.registerRider(rider)

        //4. book cab for Rider
        val driver = cabBookingSystem.bookCab(rider)
        println(
            "this cab has been booked for rider ${rider.riderName}\n" +
                    "cab id is ${driver.car?.cabId} \n" +
                    "cab name is ${driver.car?.carName} \n" +
                    "cab driver name is ${driver.driverName} \n"
        )

        //5. complete ride for driver
        val msg = cabBookingSystem.completeRide(driver)
        println(msg)

        //4. fetch history of Rider
        val listOfBooking = cabBookingSystem.fetchRiderHistory(riderId = rider.riderId)
        println("rider history is $listOfBooking")
    }
}