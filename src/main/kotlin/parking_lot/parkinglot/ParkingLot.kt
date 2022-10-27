package parking_lot.parkinglot

import parking_lot.capacity.Capacity
import parking_lot.parkingslots.ParkingSlot
import parking_lot.parkingslots.ParkingLotManager
import parking_lot.receipt.ParkingReceipt
import parking_lot.vehicle.Vehicle
import parking_lot.vehicle.VehicleType
import java.util.*
import kotlin.collections.ArrayList


class ParkingLot(private val capacity: Capacity, private val parkingLotId: Int) {
    private var slotHashMap = mutableMapOf<VehicleType, ArrayList<ParkingSlot>>()
    init {
        createHashMap()
    }

    private fun createHashMap() {
        val twoWheelerCapacity = capacity.twoWheelerCapacity
        val fourWheelerCapacity = capacity.fourWheelerCapacity
        slotHashMap[VehicleType.TWO_WHEELER] =
            mutableListOf<ParkingSlot>() as ArrayList<ParkingSlot>;
        slotHashMap[VehicleType.FOUR_WHEELER] =
            mutableListOf<ParkingSlot>() as ArrayList<ParkingSlot>;
        for (slot in 0 until twoWheelerCapacity) {
            val parkingSlot = ParkingSlot()
            parkingSlot.slotNo = slot
            parkingSlot.isSlotBooked = false
            parkingSlot.slotPricePerHour = 10.00
            slotHashMap[VehicleType.TWO_WHEELER]!!.add(parkingSlot)
        }
        val preIndex = slotHashMap[VehicleType.TWO_WHEELER]!!.size + 1
        for (slot in 0 until fourWheelerCapacity) {
            val parkingSlot = ParkingSlot()
            parkingSlot.slotNo = slot + preIndex
            parkingSlot.isSlotBooked = false
            parkingSlot.slotPricePerHour = 10.00
            slotHashMap[VehicleType.FOUR_WHEELER]!!.add(parkingSlot)
        }
    }


    fun unParkVehicle(entryParkingReceipt: ParkingReceipt): ParkingReceipt {
        return generateExitReceipt(entryParkingReceipt)
    }

    private fun generateExitReceipt(
        entryParkingReceipt: ParkingReceipt
    ): ParkingReceipt {
        val calendar = Calendar.getInstance()
        entryParkingReceipt.exitTime = calendar.timeInMillis
        Thread.sleep(2000)
        entryParkingReceipt.amtToBePaid = amtToBePaid(entryParkingReceipt)
        entryParkingReceipt.parkingSlot.isSlotBooked = false
        ParkingLotManager.createParkingHistory(entryParkingReceipt)
        return entryParkingReceipt
    }

    fun parkVehicle(vehicle: Vehicle): ParkingReceipt {
        return generateEntryReceipt(vehicle, isSlotAvailable(vehicle.vehicleType)!!)
    }

    private fun generateEntryReceipt(vehicle: Vehicle, parkingSlot: ParkingSlot): ParkingReceipt {
        parkingSlot.isSlotBooked = true
        val calendar = Calendar.getInstance()
        return ParkingReceipt(
            parkingLotId = parkingLotId,
            parkingSlot = parkingSlot,
            vehicle = vehicle,
            entryTime = calendar.timeInMillis
        )
    }

    private fun isSlotAvailable(vehicleType: VehicleType): ParkingSlot? {
        val vehicle =
            if (vehicleType == VehicleType.TWO_WHEELER) VehicleType.TWO_WHEELER else VehicleType.FOUR_WHEELER
        val parkingSlots = slotHashMap[vehicle]
        if (parkingSlots != null) {
            for (slot in parkingSlots) {
                if (!slot.isSlotBooked)
                    return slot
            }
            throw Exception("Parking lot already full")
        }
        return null
    }

    private fun amtToBePaid(entryParkingReceipt: ParkingReceipt): Double {
        val duration = entryParkingReceipt.exitTime - entryParkingReceipt.entryTime
        val seconds = (duration / 1000) % 60
        val minutes = (duration / (1000 * 60) % 60)
        val hours = (duration / (1000 * 60 * 60) % 24)
        if (duration == 0L) return entryParkingReceipt.parkingSlot.slotPricePerHour
        return duration * entryParkingReceipt.parkingSlot.slotPricePerHour
    }


}