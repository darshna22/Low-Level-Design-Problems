package book_vehicle_on_rent.model.location

class Location(
    var address: String,
    var city: String,
    var state: String,
    var country: String="India",
    var pinCode: Int,
)