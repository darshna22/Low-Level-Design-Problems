package book_vehicle_on_rent.exceptions


abstract class RentVehicleException(message: String, cause: Throwable?) : RuntimeException(message, cause) {}

class VehicleNotFoundException(message: String, cause: Throwable?) : RentVehicleException(message, cause)