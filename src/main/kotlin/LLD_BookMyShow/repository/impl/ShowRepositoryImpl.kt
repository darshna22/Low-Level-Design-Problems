package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.exception.AlreadyExistsException
import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.repository.ShowRepository
import java.util.*


class ShowRepositoryImpl: ShowRepository {
    private var showsMap = mutableMapOf<String, Show>();

    override fun getShow(showId: String): Show? {
        if (!showsMap.containsKey(showId)) {
            throw NotFoundException("show not found")
        }
        return showsMap[showId]
    }

    override fun createShow(movie: Movie, startTime: Long, showDuration: Long, seatList: List<Seat>): Show {
        if (!checkIfShowCreationAllowed(startTime, showDuration)) {
            throw AlreadyExistsException("Show already exists")
        }
        val showId: String = UUID.randomUUID().toString()
        val show = Show(showId, seatList = seatList, startTime, showDuration, movie)
        showsMap[showId] = show
        return show
    }

    override fun removeShow(showId: String) {
        showsMap.remove(showId)
    }

    override fun getAllShows(): List<Show> {
        return showsMap.values.toList()
    }


    private fun checkIfShowCreationAllowed(startTime: Long, showEndTime: Long): Boolean {
        // TODO: Implement this. This method will return whether the audi is free at a particular time for
        // specific duration. This function will be helpful in checking whether the show can be scheduled in that slot
        // or not.
        return true
    }
}