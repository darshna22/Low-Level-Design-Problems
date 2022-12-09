package LLD_CabRider.service

import LLD_CabBooking.model.Rider
import LLD_CabBooking.repository.RiderRepository


class RiderService {
    private val riderRepository = RiderRepository()

    fun addRider(driver: Rider) {
        riderRepository.addRider(driver)
    }

    fun removeRider(driverId: String) {
        riderRepository.removeRider(driverId)
    }

    fun getRiderList(): List<Rider> {
        return riderRepository.getRiderList()
    }

//    fun getRider(): Rider {
//        val riderList= riderRepository.getRiderList()
//        return
//    }


}