package LLD_BookMyShow.services

import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.Theatre
import LLD_BookMyShow.repository.ShowRepository
import java.util.*

class ShowService(private val showRepository: ShowRepository) {
    fun getShow(showId: String): Show? {
        return showRepository.getShow(showId)
    }

    fun createShow(
        movie: Movie,
        startTime: Long,
        showDurationInSeconds: Long,
        seatList: List<Seat>
    ): Show {
        return showRepository.createShow(movie, startTime, showDurationInSeconds,seatList)
    }

    fun getAllActiveShows():List<Show>{
        return showRepository.getAllShows()
    }

    fun getActiveShowsOfTheatre(theatre: Theatre):List<Show>{
        return theatre.audiList.map { it.showList }.flatten().filter { it.showStartTime > System.currentTimeMillis() }
    }


}