package com.example.parkinglot2

import parking_lot.model.Capacity
import parking_lot.model.ParkingLot
import parking_lot.ParkingLotManager
import parking_lot.model.ParkingReceipt
import parking_lot.model.Vehicle
import parking_lot.enums.VehicleType
import kotlin.jvm.JvmStatic

object ParkingLotRunner {
    @JvmStatic
    fun main(args: Array<String>) {
        val parkingLot = ParkingLot(
            Capacity(twoWheelerCapacity = 1, fourWheelerCapacity = 1),
            parkingLotId = 123
        )
        try {
            val vehicle = Vehicle(vehicleNo = "78778", vehicleType = VehicleType.FOUR_WHEELER)
            val entryReceipt = parkingLot.parkVehicle(
                vehicle
            )
            println(
                "parkingLotId: ${entryReceipt.parkingLotId}\n" +
                        "slotNo: ${entryReceipt.parkingSlot.slotNo}\n" +
                        "slotPricePerHour: ${entryReceipt.parkingSlot.slotPricePerHour}\n" +
                        "amtToBePaid: ${entryReceipt.amtToBePaid}\n" +
                        "vehicleNo: ${entryReceipt.vehicle.vehicleNo}\n" +
                        "entryTime: ${entryReceipt.entryTime}\n"
            )

            val exitReceipt = parkingLot.unParkVehicle(
                entryReceipt
            )
            println(
                        "exit receipt here \n" +
                                "parkingLotId: ${entryReceipt.parkingLotId}\n" +
                                "slotNo: ${exitReceipt.parkingSlot.slotNo}\n" +
                        "slotPricePerHour: ${exitReceipt.parkingSlot.slotPricePerHour}\n" +
                        "vehicleNo: ${exitReceipt.vehicle.vehicleNo}\n" +
                        "entryTime: ${exitReceipt.entryTime}\n" +
                        "exitTime: ${exitReceipt.exitTime}\n" +
                        "amtToBePaid: ${exitReceipt.amtToBePaid}\n"
            )
        } catch (e: Exception) {
            println(e)

        }


        val parkingLot1 = ParkingLot(
            Capacity(twoWheelerCapacity = 3, fourWheelerCapacity = 1),
            parkingLotId = 124
        )
        try {
            val vehicle = Vehicle(vehicleNo = "78778", vehicleType = VehicleType.FOUR_WHEELER)
            val entryReceipt = parkingLot1.parkVehicle(
                vehicle
            )
            println(
                "parkingLotId: ${entryReceipt.parkingLotId}\n" +
                        "slotNo: ${entryReceipt.parkingSlot.slotNo}\n" +
                        "slotPricePerHour: ${entryReceipt.parkingSlot.slotPricePerHour}\n" +
                        "amtToBePaid: ${entryReceipt.amtToBePaid}\n" +
                        "vehicleNo: ${entryReceipt.vehicle.vehicleNo}\n" +
                        "entryTime: ${entryReceipt.entryTime}\n"
            )

            val exitReceipt = parkingLot1.unParkVehicle(
                entryReceipt
            )
            println(
                "exit receipt here \n" +
                        "parkingLotId: ${entryReceipt.parkingLotId}\n" +
                        "slotNo: ${exitReceipt.parkingSlot.slotNo}\n" +
                        "slotPricePerHour: ${exitReceipt.parkingSlot.slotPricePerHour}\n" +
                        "vehicleNo: ${exitReceipt.vehicle.vehicleNo}\n" +
                        "entryTime: ${exitReceipt.entryTime}\n" +
                        "exitTime: ${exitReceipt.exitTime}\n" +
                        "amtToBePaid: ${exitReceipt.amtToBePaid}\n"
            )
        } catch (e: Exception) {
            println(e)

        }

        println("Parking lot history for particular car")
        val list = getParkingHistory("78778")
        if (list != null) {
            for (exitReceipt in list) {
                println(
                            "slotNo: ${exitReceipt.parkingSlot.slotNo}\n" +
                            "slotPricePerHour: ${exitReceipt.parkingSlot.slotPricePerHour}\n" +
                            "vehicleNo: ${exitReceipt.vehicle.vehicleNo}\n" +
                            "entryTime: ${exitReceipt.entryTime}\n" +
                            "exitTime: ${exitReceipt.exitTime}\n" +
                            "amtToBePaid: ${exitReceipt.amtToBePaid}\n"
                )
            }
        }

    }

    private fun getParkingHistory(vehicleNo: String): ArrayList<ParkingReceipt>? {
        return ParkingLotManager.getParkingHistory(vehicleNo)
    }
}