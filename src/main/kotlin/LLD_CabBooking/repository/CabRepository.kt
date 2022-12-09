package LLD_CabBooking.repository

import LLD_CabBooking.model.Booking
import LLD_CabBooking.model.cab.Car

class CabRepository {
    //key should be riderId
    private var cabMap = mutableMapOf<String, Car>()

    fun addCar(cab: Car) {
        if (!cabMap.containsKey(cab.cabId)) {
            cabMap[cab.cabId] = cab
        }
    }

    fun addAllCar(cabList: List<Car>) {
        for (car in cabList)
            addCar(car)
    }

    fun removeCar(riderId: String) {
        cabMap.remove(riderId)
    }

    fun updateCar(cab: Car) {
        if (cabMap.containsKey(cab.cabId)) {
            cabMap[cab.cabId] = cab
        }
    }

    fun getCabList(): List<Car> {
        return cabMap.values.toList()
    }

}