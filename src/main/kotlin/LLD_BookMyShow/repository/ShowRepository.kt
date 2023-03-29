package LLD_BookMyShow.repository

import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import java.util.*

interface ShowRepository {
    fun getShow(showId: String): Show?

    fun createShow(
        movie: Movie,
        startTime: Long,
        showDuration: Long,
        seatList: List<Seat>
    ): Show

    fun removeShow(showId: String)

    fun getAllShows():List<Show>


}