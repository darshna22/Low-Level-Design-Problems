package book_vehicle_on_rent.model.user

import book_vehicle_on_rent.model.location.Location

data class User(
    var userName: String,
    var userDrivingLicence: String,
    var userId: Int,
    var location: Location,
)