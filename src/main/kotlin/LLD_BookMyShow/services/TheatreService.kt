package LLD_BookMyShow.services

import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.Theatre
import LLD_BookMyTheatre.repository.TheatreRepository

class TheatreService(private val theatreRepository: TheatreRepository) {
    fun createTheatre(
        theatreName: String,
        theatreAddress: String,
        theatreCity: String,
        audiList: List<Audi>
    ) {
        theatreRepository.createTheatre(theatreName, theatreAddress, theatreCity, audiList)
    }

    fun removeTheatre(theatre: Theatre) {
        theatreRepository.removeTheatre(theatre)
    }

    fun getAllTheatre(): List<List<Theatre>> {
        return theatreRepository.getAllTheatre()
    }

    fun getByCity(city: String): List<Theatre> {
        return theatreRepository.getCityTheatre(city)
    }

    fun getById(theatreId: String): Theatre? {
       return theatreRepository.getById(theatreId)
    }

    fun addAudiToTheatre(theatreId: String, audi: Audi) {
        val theatre = theatreRepository.getById (theatreId) ?: throw NotFoundException("Theatre not found with id $theatreId")
        val audiList = theatre.audiList.toMutableList()
        audiList.add(audi)
        theatre.audiList = audiList
    }

    fun removeAudiFromTheatre(theatreId: String, audi: Audi) {
        val theatre = theatreRepository.getById (theatreId) ?: throw NotFoundException("Theatre not found with id $theatreId")
        val audiList = theatre.audiList.toMutableList()
        audiList.remove(audi)
        theatre.audiList = audiList
    }

    fun addShowToTheatreAudi(theatreId: String, audiId: String, show: Show) {
        val theatre = theatreRepository.getById (theatreId) ?: throw NotFoundException("Theatre not found with id $theatreId")
        val audi = theatre.audiList.find { it.audiId == audiId } ?: throw NotFoundException("Audi not found with id $audiId")
        val showList = audi.showList.toMutableList()
        showList.add(show)
        audi.showList = showList
    }

    fun removeShowFromTheatreAudi(theatreId: String, audiId: String, show: Show) {
        val theatre = theatreRepository.getById (theatreId) ?: throw NotFoundException("Theatre not found with id $theatreId")
        val audi = theatre.audiList.find { it.audiId == audiId } ?: throw NotFoundException("Audi not found with id $audiId")
        val showList = audi.showList.toMutableList()
        showList.add(show)
        audi.showList = showList
    }

}