package LLD_Parking_Lot

import LLD_Parking_Lot.enums.LotType
import LLD_Parking_Lot.model.lot.Capacity
import LLD_Parking_Lot.model.lot.Level
import LLD_Parking_Lot.model.lot.Lot
import LLD_Parking_Lot.model.lot.ParkingLot
import LLD_Parking_Lot.model.vehicle.Car
import kotlin.random.Random

fun main() {
    val parkingLot = ParkingLot(
        id = Random.nextLong(), levelList = mutableListOf(
            Level(
                levelId = Random.nextLong(),
                listOfLots = mutableListOf(
                    Lot(Random.nextLong(), LotType.CAR, true, 10.0),
                    Lot(Random.nextLong(), LotType.CAR, true, 10.0),
                    Lot(Random.nextLong(), LotType.TRUCK, true, 10.0),
                    Lot(Random.nextLong(), LotType.TRUCK, true, 10.0),
                    Lot(Random.nextLong(), LotType.BIKE, true, 10.0),
                    Lot(Random.nextLong(), LotType.BIKE, true, 10.0)
                ),
                capacity = Capacity(totalBikeLot = 2, totalCarLot = 2, totalTruckLot = 2),
            )
        ), name = "Test Parking Lot", entryGates = 1, exitGates = 1
    )

    val parkingLotManager = ParkingLotManager(parkingLotList = mutableListOf(parkingLot))
    val vehicle = Car(id = Random.nextLong(), name = "Nexon")

    val receipt = parkingLotManager.registerVehicle(vehicle = vehicle)
    println("============Entry Receipt============")
    println(receipt.parkingLotId)
    println(receipt.parkingLot.lotId)
    println(receipt.parkingLot.lotType)
    println(receipt.parkingLot.lotPricePerHour)
    println(receipt.vehicle.id)
    println(receipt.vehicle.name)
    println(receipt.vehicle.vehicleType)
    println(receipt.entryTime)
    println("============Exit Receipt============")
    val receipt1 = parkingLotManager.unRegisterVehicle(receipt)
    println(receipt1.parkingLotId)
    println(receipt1.parkingLot.lotId)
    println(receipt1.parkingLot.lotType)
    println(receipt1.parkingLot.lotPricePerHour)
    println(receipt1.vehicle.id)
    println(receipt1.vehicle.name)
    println(receipt1.vehicle.vehicleType)
    println(receipt1.entryTime)
    println(receipt1.exitTime)
    println(receipt1.amtToBePaid)


}