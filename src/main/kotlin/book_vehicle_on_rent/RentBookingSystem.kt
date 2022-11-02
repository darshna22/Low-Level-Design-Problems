package book_vehicle_on_rent

import book_vehicle_on_rent.enums.ReservationStatus
import book_vehicle_on_rent.model.reservation.Reservation
import book_vehicle_on_rent.model.store.Store
import book_vehicle_on_rent.model.user.User
import book_vehicle_on_rent.service.ReservationHistoryService
import book_vehicle_on_rent.service.ReservationService
import book_vehicle_on_rent.service.StoreService
import book_vehicle_on_rent.service.UserService

class RentBookingSystem(val mStoreList: MutableList<Store>) {
    private var userService: UserService = UserService()
    private var storeService: StoreService = StoreService(mStoreList)
    private var reservationService: ReservationService =ReservationService()
    private val reservationHistoryService: ReservationHistoryService = ReservationHistoryService()

    fun addUsers(user: User) {
        userService.addUsers(user)
    }

    fun addStore(store: Store) {
        storeService.addStore(store)
    }

    fun removeUser(user: User) {
        userService.removeUser(user)
    }

    fun removeStore(store: Store) {
        storeService.removeStore(store)
    }


    fun reserveVehicle(reservation: Reservation) {
        //add reservation to reservation list
        reservationService.addReservation(reservation)

        //create reservation history for user
        createReservationHistory(reservation)

        //update store vehicle list
        val storeList = storeService.getStoreList().filter { it.storeId == reservation.storeId }
        storeList[0].vehicleInventoryManager?.removeVehicleFromList(reservation.vehicle)
    }

    fun getStoreListBy(value: String): List<Store> {
        val storeList = storeService.getStoreList()
        val cityWiseList = storeList.filter { it.storeLocation!!.city == value }
        if (cityWiseList.isNotEmpty()) return cityWiseList
        val stateWiseList = storeList.filter { it.storeLocation!!.state == value }
        if (stateWiseList.isNotEmpty()) return stateWiseList
        val pinCodeWiseList = storeList.filter { it.storeLocation!!.pinCode.toString() == value }
        if (pinCodeWiseList.isNotEmpty()) return pinCodeWiseList
        return emptyList()
    }

    private fun createReservationHistory(reservation: Reservation) {
        addUsers(reservation.user)
        reservationHistoryService.createReservationHistory(reservation)
    }

    fun getVehicleReservationHistory(userID: Int): List<Reservation>? {
        return reservationHistoryService.getReservationHistory()[userID]
    }

    //update both list after payment
    fun updateReservationAndVehicleList(reservation: Reservation) {
        reservation.reservationStatus = ReservationStatus.COMPLETED
        //remove vehicle from reservation list of system
        reservationService.removeReservation(reservation)
        //add remove reservation vehicle to store vehicle list again
        val store = storeService.getStoreList().filter { it.storeId == reservation.storeId }
        store[0].vehicleInventoryManager?.addVehicleToList(reservation.vehicle)


    }


}