package LLD_BookMyShow.managers

import LLD_BookMyShow.constants.Constants
import LLD_BookMyShow.enums.SeatStatusType
import LLD_BookMyShow.enums.SeatType
import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.repository.impl.MovieRepositoryImpl
import LLD_BookMyShow.repository.impl.ShowRepositoryImpl
import LLD_BookMyShow.services.*
import LLD_BookMyShow.repository.impl.TheatreRepositoryImpl
import LLD_BookMyTheatre.repository.TheatreRepository
import java.util.*


class TheatreManager (private val movieService: MovieService,
                      private val theatreService: TheatreService,
                      private val showService: ShowService) {
    public fun init() {
        createTheater("MyTheatre", "Kormanngala", "Bangalore")
    }

    private fun createTheater(theatreName: String, theatreAddress: String, theatreCity: String) {
        //Step:2 create seat for show
        val showSeats = createSeats();
        //Step:2 create show2 for audi
        val showList = createShows(showSeats)
        val audiList = createAudi(showList)
        //create theatre
        theatreService.createTheater(
            theatreName = theatreName,
            theatreAddress = theatreAddress,
            theatreCity = "Bangalore",
            audiList = audiList
        )


    }

    private fun createSeats(): List<Seat> {
        val showSeats = mutableListOf<Seat>();
        for (i in 1..Constants.DIAMOND_SEAT_COUNT) {
            showSeats.add(Seat(
                seatId = UUID.randomUUID().toString(),
                seatNo = i,
                seatRow = 10,
                seatType = SeatType.DIAMOND,
                price = SeatType.DIAMOND.value * 100L,
                seatStatus = SeatStatusType.AVAILABLE
            ))
        }

        for (i in 1..Constants.GOLD_SEAT_COUNT) {
            showSeats.add(Seat(
                seatId = UUID.randomUUID().toString(),
                seatNo = i,
                seatRow = 9,
                seatType = SeatType.GOLD,
                price = SeatType.GOLD.value * 100L,
                seatStatus = SeatStatusType.AVAILABLE
            ))
        }

        for (i in 1..Constants.SILVER_SEAT_COUNT) {
            showSeats.add(Seat(
                seatId = UUID.randomUUID().toString(),
                seatNo = i,
                seatRow = 10,
                seatType = SeatType.SILVER,
                price = SeatType.SILVER.value * 100L,
                seatStatus = SeatStatusType.AVAILABLE
            ))
        }
        return showSeats.toList()
    }

    private fun createAudi(showList: List<Show>): List<Audi> {
        val audi1 = Audi(
            audiId = UUID.randomUUID().toString(),
            audiName = "Audi1",
            showList = showList,
        )
        return listOf(audi1);
    }

    private fun createShows(seats: List<Seat>): List<Show> {
        val showDuration = 120*60; // minutes  * seconds
        val showStartTime1 = System.currentTimeMillis() + showDuration
        showService.createShow(
            movie = movieService.createMovie(movieName = "AVENGERS"),
            seatList = seats,
            showDurationInSeconds = showDuration.toLong(),
            startTime = showStartTime1
        )
        showService.createShow(
            movie = movieService.createMovie(movieName = "BAHUBALI2"),
            seatList = seats,
            showDurationInSeconds = showDuration.toLong(),
            startTime = showStartTime1 + showDuration
        )
        return showService.getAllActiveShows()
    }

    fun addAudiToTheatre(theatreId: String, audi: Audi) {
        theatreService.addAudiToTheatre(theatreId, audi)
    }

    fun removeAudiFromTheatre(theatreId: String, audi: Audi) {
        theatreService.removeAudiFromTheatre(theatreId, audi)
    }

    fun addShowToTheatreAudi(theatreId: String, audiId: String, show: Show) {
        theatreService.addShowToTheatreAudi(theatreId, audiId, show)
    }

    fun removeShowFromTheatreAudi(theatreId: String, audiId: String, show: Show) {
        theatreService.removeShowFromTheatreAudi(theatreId, audiId, show)
    }

}