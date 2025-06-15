package LLD_Parking_Lot

import LLD_Parking_Lot.enums.LotType
import LLD_Parking_Lot.enums.VehicleType
import LLD_Parking_Lot.model.lot.AvailableLot
import LLD_Parking_Lot.model.lot.ParkingLot
import LLD_Parking_Lot.model.vehicle.Vehicle

class ParkingLotManager(
    private val parkingLotList: List<ParkingLot> = listOf()
) {
    private val vehicleLotMap = mutableMapOf<Vehicle, AvailableLot>()

    fun getAvailableLotsCount(lotType: LotType): Int = getAvailabilityByType()[lotType] ?: 0

    fun getAvailabilityByType(): Map<LotType, Int> {
        val counts = mutableMapOf<LotType, Int>()
        val levelList = parkingLotList.flatMap { it.levelList }
        for (lot in levelList.flatMap { it.listOfLots }) {
            if (lot.isLotAvailable) {
                counts[lot.lotType] = counts.getOrDefault(lot.lotType, 0) + 1
            }
        }
        return counts
    }


    @Synchronized //for multithreading
    fun registerVehicle(vehicle: Vehicle): ParkingReceipt {
        if (vehicleLotMap.containsKey(vehicle)) {
            throw IllegalStateException("Vehicle is already registered")
        }

        val availableLot = findAvailableLot(vehicle.vehicleType)
            ?: throw IllegalStateException("No available parking lot for vehicle type ${vehicle.vehicleType}")

        availableLot.lot.isLotAvailable = false

        vehicleLotMap[vehicle] = availableLot
        return createEntryReceipt(availableLot, vehicle)
    }

    private fun createEntryReceipt(availableLot: AvailableLot, vehicle: Vehicle): ParkingReceipt {
        return ParkingReceipt(
            parkingLotId = availableLot.parkingLotId,
            parkingLot = availableLot.lot,
            vehicle = vehicle,
            entryTime = System.currentTimeMillis()
        )
    }

    @Synchronized // for multithreading
    fun unRegisterVehicle(entryReceipt: ParkingReceipt): ParkingReceipt {
        val vehicle = vehicleLotMap.keys.find { it.id == entryReceipt.vehicle.id }
            ?: throw IllegalArgumentException("Vehicle with ID ${entryReceipt.vehicle.id} is not registered")

        val lot = vehicleLotMap[vehicle]?.lot
        lot?.isLotAvailable = true
        vehicleLotMap.remove(vehicle)
        return createExitReceipt(entryReceipt)
    }

    fun findAvailableLot(vehicleType: VehicleType): AvailableLot? {
        for (parkingLot in parkingLotList) {
            for (level in parkingLot.levelList) {
                for (lot in level.listOfLots) {
                    if (lot.isLotAvailable && lot.lotType.matches(vehicleType)) {
                        return AvailableLot(level, lot, parkingLotId = parkingLot.id)
                    }
                }
            }
        }
        return null
    }

    private fun createExitReceipt(entryReceipt: ParkingReceipt): ParkingReceipt {
        entryReceipt.exitTime = System.currentTimeMillis()
        entryReceipt.amtToBePaid =
            (entryReceipt.exitTime - entryReceipt.entryTime) * entryReceipt.parkingLot.lotPricePerHour
        return entryReceipt
    }

    private fun LotType.matches(vehicleType: VehicleType): Boolean {
        return when (vehicleType) {
            VehicleType.BIKE -> this == LotType.BIKE
            VehicleType.CAR -> this == LotType.CAR
            VehicleType.TRUCK -> this == LotType.TRUCK
        }
    }

    fun getRegisteredVehicles(): List<Vehicle> = vehicleLotMap.keys.toList()

    fun getOccupiedLots(): List<AvailableLot> = vehicleLotMap.values.toList()
}
