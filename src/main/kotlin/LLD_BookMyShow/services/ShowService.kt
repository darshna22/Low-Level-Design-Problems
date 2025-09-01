package LLD_BookMyShow.services

import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.Theater
import LLD_BookMyShow.repository.ShowRepository
import java.util.Date

class ShowService(private val showRepository: ShowRepository) {

    fun createShow(
        name: String,
        movie: Movie,
        startTime: Date,
        showDurationInSeconds: Long,
        seatList: List<Seat>
    ) {
        showRepository.createShow(name, movie, startTime, showDurationInSeconds, seatList)
    }

    fun getAllActiveShows(): List<Show> {
        return showRepository.getAllShows()
    }

    fun getShowById(showId: String): Show {
        return showRepository.getShow(showId)
    }
}