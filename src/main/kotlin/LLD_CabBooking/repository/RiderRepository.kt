package LLD_CabBooking.repository

import LLD_CabBooking.model.Driver
import LLD_CabBooking.model.Rider

class RiderRepository {
    //key should be riderId
    private var riderMap = mutableMapOf<String, Rider>()

    fun addRider(rider: Rider) {
        if (!riderMap.containsKey(rider.riderId)) {
            riderMap[rider.riderId] = rider
        }
    }

    fun removeRider(riderId: String) {
        riderMap.remove(riderId)
    }

    fun getRiderList(): List<Rider> {
        return riderMap.values.toList()
    }
}