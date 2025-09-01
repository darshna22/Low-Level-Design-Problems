package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.exception.AlreadyExistsException
import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.exception.ShowCreationNotAllowedException
import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.repository.ShowRepository
import java.util.Date
import java.util.UUID


class ShowRepositoryImpl : ShowRepository {
    private var showsMap = mutableMapOf<String, Show>();

    override fun createShow(
        name: String,
        movie: Movie,
        startTime: Date,
        showDuration: Long,
        seatList: List<Seat>
    ) {
        if (!checkIfShowCreationAllowed(startTime, showDuration)) {
            throw ShowCreationNotAllowedException("Show creation not allowed")
        }
        if (showsMap.containsKey(movie.movieId)) {
            throw AlreadyExistsException("Show already exists for movie ${movie.movieName}")
        }
        val show = getShow(name, movie, startTime, showDuration, seatList)
        showsMap[show.showId] = getShow(name, movie, startTime, showDuration, seatList)
    }

    private fun getShow(
        name: String,
        movie: Movie,
        showStartTime: Date,
        showDuration: Long,
        seatList: List<Seat>
    ): Show {
        return Show(
            showId = UUID.randomUUID().toString(),
            showName = name,
            seatList = seatList,
            showStartTime = showStartTime,
            showDurationInSeconds = showDuration,
            movie
        )
    }


    override fun removeShow(showId: String) {
        showsMap.remove(showId)
    }

    override fun getAllShows(): List<Show> {
        return showsMap.values.toList()
    }

    override fun getShow(showId: String): Show {
        return showsMap[showId]?:throw NotFoundException("Show not found")
    }

    override fun getShow(): Show {
        TODO("Not yet implemented")
    }


    private fun checkIfShowCreationAllowed(startTime: Date, showEndTime: Long): Boolean {
        // TODO: Implement this. This method will return whether the audi is free at a particular time for
        // specific duration. This function will be helpful in checking whether the show can be scheduled in that slot
        // or not.
        return true
    }
}