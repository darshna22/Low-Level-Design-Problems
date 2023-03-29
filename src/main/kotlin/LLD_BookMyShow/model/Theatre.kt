package LLD_BookMyShow.model

data class Theatre(
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    var audiList: List<Audi>
)
