package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.exception.AlreadyExistsException
import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.Theatre
import LLD_BookMyTheatre.repository.TheatreRepository
import java.util.*

class TheatreRepositoryImpl : TheatreRepository {
    //key should be TheatreID
    private var cityTheatreMap = mutableMapOf<String, List<Theatre>>()

    override fun createTheatre(
        theatreName: String,
        theatreAdd: String,
        theatreCity: String,
        audiList: List<Audi>
    ) {

        var theatreList = mutableListOf<Theatre>()
        if (cityTheatreMap.containsKey(theatreCity)) {
            theatreList = cityTheatreMap[theatreCity] as MutableList<Theatre>
            theatreList.forEach {
                if (it.name == theatreName) {
                    throw AlreadyExistsException("Theatre already exists with name $theatreName")
                }
            }
        }

        val theatre = Theatre(
            id = UUID.randomUUID().toString(),
            name = "Nexus",
            address = "Kormangala",
            city = "Bangalore",
            audiList = audiList
        )
        theatreList.add(theatre)
        cityTheatreMap[theatre.city] = theatreList

    }

    override fun removeTheatre(theatre: Theatre) {
        if (cityTheatreMap.containsKey(theatre.city)) {
            val theatreList = cityTheatreMap[theatre.city] as MutableList<Theatre>
            run breaking@{
                theatreList.forEach {
                    if (theatre.id == it.id) {
                        theatreList.remove(theatre)
                        return@breaking
                    }
                }
            }
            cityTheatreMap[theatre.city] = theatreList
        }

    }

    override fun getAllTheatre(): List<List<Theatre>> {
        return cityTheatreMap.values.toList()
    }

    override fun getCityTheatre(city: String): List<Theatre> {
        return cityTheatreMap[city]?.toList() ?: emptyList()
    }

    override fun getById(theatreId: String): Theatre? {
        return getAllTheatre().flatten().find { it.id == theatreId };
    }

}