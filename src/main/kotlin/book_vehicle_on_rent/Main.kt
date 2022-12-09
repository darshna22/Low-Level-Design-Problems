package book_vehicle_on_rent

import book_vehicle_on_rent.utility.Utility.getDate
import book_vehicle_on_rent.utility.Utility.storesList
import book_vehicle_on_rent.model.bill.BillService
import book_vehicle_on_rent.enums.PaymentMode
import book_vehicle_on_rent.model.reservation.Reservation
import book_vehicle_on_rent.enums.ReservationStatus
import book_vehicle_on_rent.enums.ReservationType
import book_vehicle_on_rent.model.location.Location
import book_vehicle_on_rent.model.user.User
import book_vehicle_on_rent.model.store.Store
import book_vehicle_on_rent.service.payment_service.PaymentService

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val rentBookingSystem = RentBookingSystem(mStoreList = storesList() as MutableList<Store>)

        //1. user open app to book vehicle
        //and user search store by location eg; banglore/agra/delhi etc
        val storeList = rentBookingSystem.getStoreListBy("Bangalore")
        var storeNo = 1
        for (store in storeList) {
            println("Store No=$storeNo with below Vehicle List:")
            for (vehicle in store.vehicleInventoryManager!!.vehicleList)
                println(
                    "Vehicle Type=${vehicle.vehicleType} \n" +
                            "Vehicle No=${vehicle.vehicleN0} \n" +
                            "Vehicle Company=${vehicle.vehicleCompany} \n" +
                            "Vehicle Daily Rate=${vehicle.dailyVehicleRate} \n" +
                            "Vehicle Hourly Rate=${vehicle.hourlyVehicleRate} \n" +
                            "No of Seats in vehicle=${vehicle.noOfSeat} \n" +
                            "Vehicle Status=${vehicle.status} \n"
                )
            storeNo++
        }

        //2. user will reserve vehicle
        val userInfo = User(
            userId = 1,
            userName = "darshna",
            userDrivingLicence = "6765",
            location = Location(
                address = "Bren Edge Waters Society",
                city = "Bangalore",
                state = "Karnataka",
                pinCode = 560035
            )
        )

        val reservation = Reservation(
            storeId = 1,
            reservationId = 1,
            user = userInfo,
            vehicle = storeList[0].vehicleInventoryManager!!.vehicleList[0],
            bookingDate = getDate(),
            dateBookedFrom = getDate(false, 1),
            dateBookedTo = getDate(false, 3),
            pickUpLocation = storeList[0].storeLocation,
            dropLocation = storeList[0].storeLocation,
            reservationType = ReservationType.DAILY,
            reservationStatus = ReservationStatus.INPROGRESS
        )
        rentBookingSystem.reserveVehicle(reservation)

        //3. generate Bill for reserve vehicle
        BillService().calculateBillAmt(reservation = reservation)
        println(
            "Reservation ID=${reservation.reservationId} \n" +
                    "Reserve from =${reservation.storeId} \n" +
                    "Reserve for =${reservation.user.userName} \n" +
                    "Reserve Date =${reservation.bookingDate} \n" +
                    "Reserve from date=${reservation.dateBookedFrom} \n" +
                    "Reserve to date =${reservation.dateBookedTo} \n" +
                    "Pickup location =${reservation.pickUpLocation} \n" +
                    "Drop location =${reservation.dropLocation} \n" +
                    "Reserve Type =${reservation.reservationType} \n" +
                    "Reserve Status =${reservation.reservationStatus} \n" +
                    "Reserve vehicle bill amt to be paid is ${reservation.reservationAmt}"
        )


        //4. pay amt
        val paymentService = PaymentService()
        paymentService.payWith(reservation, PaymentMode.CREDIT_CARD)
        if (reservation.isAmtPaid)
            println("payment done for vehicle reservation")
        println(
            "Reservation ID=${reservation.reservationId} \n" +
                    "Reserve from =${reservation.storeId} \n" +
                    "Reserve for =${reservation.user.userName} \n" +
                    "Reserve Date =${reservation.bookingDate} \n" +
                    "Reserve from date=${reservation.dateBookedFrom} \n" +
                    "Reserve to date =${reservation.dateBookedTo} \n" +
                    "Pickup location =${reservation.pickUpLocation} \n" +
                    "Drop location =${reservation.dropLocation} \n" +
                    "Reserve Type =${reservation.reservationType} \n" +
                    "Reserve Status =${reservation.reservationStatus} \n" +
                    "Reserve Amt paid =${reservation.isAmtPaid}"
        )

        //5. return vehicle
        println("Reserve vehicle has been returned")
        rentBookingSystem.updateReservationAndVehicleList(reservation)
        println(
            "Reservation ID=${reservation.reservationId} \n" +
                    "Reserve from =${reservation.storeId} \n" +
                    "Reserve for =${reservation.user.userName} \n" +
                    "Reserve Date =${reservation.bookingDate} \n" +
                    "Reserve from date=${reservation.dateBookedFrom} \n" +
                    "Reserve to date =${reservation.dateBookedTo} \n" +
                    "Pickup location =${reservation.pickUpLocation} \n" +
                    "Drop location =${reservation.dropLocation} \n" +
                    "Reserve Type =${reservation.reservationType} \n" +
                    "Reserve Status =${reservation.reservationStatus} \n"
        )


    }


}