package LLD_BookMyShow

import LLD_BookMyShow.enums.SeatType
import LLD_BookMyShow.managers.TheatreManager
import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.User
import LLD_BookMyShow.repository.impl.BookingRepositoryImpl
import LLD_BookMyShow.repository.impl.MovieRepositoryImpl
import LLD_BookMyShow.repository.impl.ShowRepositoryImpl
import LLD_BookMyShow.repository.impl.TheatreRepositoryImpl
import LLD_BookMyShow.services.BookingService
import LLD_BookMyShow.services.MovieService
import LLD_BookMyShow.services.ShowService
import LLD_BookMyShow.services.TheatreService
import java.util.UUID

fun main() {
    val showRepository = ShowRepositoryImpl()
    val movieService = MovieService(MovieRepositoryImpl())
    val showService = ShowService(showRepository)
    val theatreService = TheatreService(TheatreRepositoryImpl())

    val bookingService = BookingService(BookingRepositoryImpl())
    TheatreManager(
        movieService = movieService,
        theatreService = theatreService,
        showService = showService).init()


    val user = User(
        userId = UUID.randomUUID().toString(),
        userName = "Darshna",
        city = "Bangalore"
    )

    val theatre = theatreService.getByCity(user.city)
    val availableShows = showService.getActiveShowsOfTheatre(theatre.first())
    val bookingRequest = Booking(
        bookingId = UUID.randomUUID().toString(),
        bookingByUser = user,
        bookingShow = availableShows.first(),
        bookingDateAndTime = System.currentTimeMillis(),
        bookedSeats = availableShows.first().seatList.filter { it.seatType == SeatType.DIAMOND }.take(2)
    )
    bookingService.addBooking(bookingRequest)
    bookingService.getAllUserBookings(user)
    val nowAvailableShows = showService.getActiveShowsOfTheatre(theatre.first())

    val bookingRequest2 = Booking(
        bookingId = UUID.randomUUID().toString(),
        bookingByUser = user,
        bookingShow = nowAvailableShows.first(),
        bookingDateAndTime = System.currentTimeMillis(),
        bookedSeats = nowAvailableShows.first().seatList.filter { it.seatType == SeatType.DIAMOND }.take(2)
    )
    // same seats cannot be booked which were confirmed already
    //bookingService.addBooking(bookingRequest2)
}