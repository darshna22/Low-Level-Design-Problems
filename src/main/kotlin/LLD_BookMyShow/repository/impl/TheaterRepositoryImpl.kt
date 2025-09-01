package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.exception.AlreadyExistsException
import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.Theater
import LLD_BookMyTheater.repository.TheaterRepository
import java.util.*

class TheaterRepositoryImpl : TheaterRepository {
    //key should be TheaterID
    private var theaterCityMap =
        mutableMapOf<String, List<Theater>>() //theaterCity , list of theater


    override fun createTheater(
        theaterName: String,
        theaterAdd: String,
        theaterCity: String,
        audiList: List<Audi>
    ) {

        if (theaterCityMap.containsKey(theaterCity)) {
            val theaterList = theaterCityMap[theaterCity] as MutableList<Theater>
            theaterList.forEach {
                if (it.name == theaterName) {
                    throw AlreadyExistsException("Theater already exists with name $theaterName")
                }
            }
        }

        val theater = Theater(
            id = UUID.randomUUID().toString(),
            name = "Nexus",
            address = "Kormangala",
            city = "Bangalore",
            audiList = audiList as MutableList<Audi>
        )
        addTheaterToCityMap(theater)

    }

    private fun addTheaterToCityMap(theater: Theater) {
        val theaterCityList: MutableList<Theater> =
            theaterCityMap.getOrPut(theater.city) { mutableListOf() } as MutableList<Theater>
        theaterCityList.add(theater)
        theaterCityMap[theater.city] = theaterCityList
    }

    override fun removeTheater(theater: Theater) {
        if (theaterCityMap.containsKey(theater.city)) {
            val theaterCityList = theaterCityMap[theater.city] as MutableList<Theater>
            val theaterList = theaterCityMap[theater.city] as MutableList<Theater>

            run breaking@{
                theaterCityList.forEach {
                    if (theater.id == it.id) {
                        theaterCityList.remove(theater)
                        theaterList.remove(theater)
                        return@breaking
                    }
                }
            }
            theaterCityMap[theater.city] = theaterCityList

        } else {
            throw NotFoundException("Theater not found with id ${theater.id} or ${theater.city}")

        }

    }

    //use flatten method to convert collection list of list to single list
    override fun getAllTheater(): List<Theater> {
        return theaterCityMap.values.flatten()
    }

    override fun getCityTheater(city: String): List<Theater> {
        return theaterCityMap[city] ?: throw NotFoundException("Theater not found with city $city")
    }

    override fun getById(theaterId: String): Theater {
        val allTheaters = getAllTheater()
        return allTheaters.find { it.id == theaterId }
            ?: throw NotFoundException("Theater not found with id $theaterId")
    }

    fun getActiveShowsOfTheater(theater: Theater): List<Show> {
        return theater.audiList.flatMap { it.showList }//this will convert all list to one list
    }

}