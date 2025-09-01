package LLD_BookMyShow.services

import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.Theater
import LLD_BookMyTheater.repository.TheaterRepository

class TheaterService(private val theaterRepository: TheaterRepository) {
    private var theaterMap: Map<String, List<Theater>> = mutableMapOf()
    fun createTheater(
        theaterName: String,
        theaterAddress: String,
        theaterCity: String,
        audiList: List<Audi>
    ) {
        theaterRepository.createTheater(theaterName, theaterAddress, theaterCity, audiList)
    }

    fun removeTheater(theater: Theater) {
        theaterRepository.removeTheater(theater)
    }

    fun getAllTheater(): List<Theater> {
        return theaterRepository.getAllTheater()
    }

    fun getByCity(city: String): List<Theater> {
        return theaterRepository.getCityTheater(city)
    }

    fun getById(theaterId: String): Theater? {
        return theaterRepository.getById(theaterId)
    }

    fun addAudiToTheater(theaterId: String, audi: Audi) {
        val theater = theaterRepository.getById(theaterId)
            ?: throw NotFoundException("Theater not found with id $theaterId")
        val audiList = theater.audiList.toMutableList()
        audiList.add(audi)
        theater.audiList = audiList
    }

    fun removeAudiFromTheater(theaterId: String, audi: Audi) {
        val theater = theaterRepository.getById(theaterId)
            ?: throw NotFoundException("Theater not found with id $theaterId")
        val audiList = theater.audiList.toMutableList()
        audiList.remove(audi)
        theater.audiList = audiList
    }

    fun addShowToTheaterAudi(theaterId: String, audiId: String, show: Show) {
        val theater = theaterRepository.getById(theaterId)
            ?: throw NotFoundException("Theater not found with id $theaterId")
        val audi = theater.audiList.find { it.audiId == audiId }
            ?: throw NotFoundException("Audi not found with id $audiId")
        val showList = audi.showList.toMutableList()
        showList.add(show)
        audi.showList = showList
    }

    fun removeShowFromTheaterAudi(theaterId: String, audiId: String, show: Show) {
        val theater = theaterRepository.getById(theaterId)
            ?: throw NotFoundException("Theater not found with id $theaterId")
        val audi = theater.audiList.find { it.audiId == audiId }
            ?: throw NotFoundException("Audi not found with id $audiId")
        val showList = audi.showList.toMutableList()
        showList.add(show)
        audi.showList = showList
    }

}