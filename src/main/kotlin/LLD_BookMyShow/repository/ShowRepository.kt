package LLD_BookMyShow.repository

import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import java.util.Date

interface ShowRepository {
    fun getShow(showId: String): Show

    fun createShow(
        name: String,
        movie: Movie,
        startTime: Date,
        showDuration: Long,
        seatList: List<Seat>
    )

    fun removeShow(showId: String)

    fun getAllShows(): List<Show>

    fun getShow(): Show


}