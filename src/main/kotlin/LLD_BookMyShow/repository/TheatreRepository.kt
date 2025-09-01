package LLD_BookMyTheater.repository

import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Theater

interface TheaterRepository {

    fun createTheater(
        theaterName: String,
        theaterAdd: String,
        theaterCity: String,
        audiList: List<Audi>
    )

    fun removeTheater(theater: Theater)

    fun getAllTheater(): List<Theater>

    fun getCityTheater(city: String): List<Theater>

    fun getById(theaterId: String): Theater?
}