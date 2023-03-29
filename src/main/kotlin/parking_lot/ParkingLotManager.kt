package parking_lot

import parking_lot.model.ParkingReceipt

object ParkingLotManager {
    private var parkingHistoryHashMap = mutableMapOf<String, ArrayList<ParkingReceipt>>()
    private var parkingHistoryList = mutableListOf<ParkingReceipt>()

    fun createParkingHistory(parkingReceipt: ParkingReceipt){
        parkingHistoryList.add(parkingReceipt)
        parkingHistoryHashMap[parkingReceipt.vehicle.vehicleNo] = parkingHistoryList as ArrayList<ParkingReceipt>
    }

    fun getParkingHistory(vehicleNo: String): ArrayList<ParkingReceipt>? {
       return parkingHistoryHashMap[vehicleNo]
    }

}