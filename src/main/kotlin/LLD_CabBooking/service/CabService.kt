package LLD_CabCar.service

import LLD_CabBooking.model.cab.Car
import LLD_CabBooking.repository.CabRepository

class CabService {
    private val carRepository = CabRepository()

    fun addCar(car: Car) {
        carRepository.addCar(car)
    }

    fun addAllCar(carList: List<Car>) {
        carRepository.addAllCar(carList)
    }

    fun removeCar(carId: String) {
        carRepository.removeCar(carId)
    }

    fun updateCar(car: Car) {
        carRepository.updateCar(car)
    }

    fun updateCarLocation(car: Car) {
        carRepository.updateCar(car)
    }

    fun getCarList(): List<Car> {
        return carRepository.getCabList()
    }


}