package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.enums.SeatStatusType
import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.exception.SeatNotAvailableException
import LLD_BookMyShow.exception.SeatPermanentlyNotAvailableException
import LLD_BookMyShow.exception.SeatTemporarilyNotAvailableException
import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Booking
import LLD_BookMyShow.model.Seat
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.User
import LLD_BookMyShow.provider.SeatLockProvider
import LLD_BookMyShow.repository.BookingRepository
import java.util.Date
import java.util.UUID


class BookingRepositoryImpl(
    private val seatLockProvider: SeatLockProvider
) : BookingRepository {
    //key should be riderId
    private var userBookingMap = mutableMapOf<String, List<Booking>>() //user id with booking list

    override fun createBooking(
        bookingByUser: User,
        bookingShow: Show,
        bookingDateAndTime: Date,
        selectedSeats: List<Seat>,
        showAudi: Audi
    ) {
        checkSeatStatus(selectedSeats, show = bookingShow, user = bookingByUser)

        val booking =
            getCreatedBooking(
                bookingByUser,
                bookingShow,
                bookingDateAndTime,
                selectedSeats,
                showAudi
            )
        seatLockProvider.temporarilyLockSeats(
            show = bookingShow,
            seatsList = selectedSeats,
            user = bookingByUser
        )
        addBooking(booking)
    }

    private fun checkSeatStatus(selectedSeats: List<Seat>, show: Show, user: User) {
        if (isAnySeatAlreadyBooked(show, selectedSeats)) {
            var unAvailableSeats = ""
            val tempSeatList =
                seatLockProvider.getTemporarilyLockSeats(show = show, selectedSeats, user)
            val filterPermanentSelectedSeats =
                getFilteredPermanentSelectedSeats(show, selectedSeats)

            if (tempSeatList.isNotEmpty() && filterPermanentSelectedSeats.isNotEmpty()) {
                tempSeatList.forEach {
                    unAvailableSeats += it.seatRow.toString() + "," + it.seatNo.toString() + "," + it.seatStatus + "\n"
                }

                filterPermanentSelectedSeats.forEach {
                    unAvailableSeats += it.seatRow.toString() + "," + it.seatNo.toString() + "," + it.seatStatus + "\n"
                }

                throw SeatNotAvailableException("Seat already locked by other user\n$unAvailableSeats")

            } else if (tempSeatList.isNotEmpty()) {
                tempSeatList.forEach {
                    unAvailableSeats += it.seatRow.toString() + "," + it.seatNo.toString() + "\n"
                }
                throw SeatTemporarilyNotAvailableException("Seat already locked by other user\n$unAvailableSeats")

            } else if (filterPermanentSelectedSeats.isNotEmpty()) {
                filterPermanentSelectedSeats.forEach {
                    unAvailableSeats += it.seatRow.toString() + "," + it.seatNo.toString() + "," + it.seatStatus + "\n"
                }
                throw SeatPermanentlyNotAvailableException("Seat already locked by other user\n$unAvailableSeats")

            }
        }
    }

    private fun getFilteredPermanentSelectedSeats(
        show: Show,
        selectedSeats: List<Seat>
    ): List<Seat> {
        val permanentSeatList =
            getAllUserBookings().filter { booking -> booking.bookingShow.showId == show.showId }
                .flatMap { it.bookedSeats }
        val filterPermanentSelectedSeats = selectedSeats.filter { permanentSeatList.contains(it) }
        return filterPermanentSelectedSeats
    }

    private fun getCreatedBooking(
        bookingByUser: User,
        bookingShow: Show,
        bookingDateAndTime: Date,
        bookedSeats: List<Seat>,
        showAudi: Audi
    ) = Booking(
        bookingId = UUID.randomUUID().toString(),
        bookingByUser = bookingByUser,
        bookingShow = bookingShow,
        bookingDateAndTime = bookingDateAndTime,
        bookedSeats = bookedSeats,
        showAudi = showAudi
    )


    private fun addBooking(booking: Booking) {
        val bookingList: MutableList<Booking> =
            userBookingMap.getOrPut(booking.bookingByUser.userId) { mutableListOf() } as MutableList<Booking>
        bookingList.add(booking)
        userBookingMap[booking.bookingByUser.userId] = bookingList

    }

    override fun removeBooking(userId: String, bookingId: String) {
        //this will throw exception if user not have any booking
        val bookingList = getUserBookings(userId) as MutableList<Booking>
        bookingList.removeIf { it.bookingId == bookingId }
        userBookingMap[userId] = bookingList
    }

    override fun updateBooking(booking: Booking) {
        getAllUserBookings().find { it.bookingId == booking.bookingId }
            ?: throw NotFoundException("Booking not found with id ${booking.bookingId}")

        val bookingList = getUserBookings(booking.bookingByUser.userId) as MutableList<Booking>
        bookingList.remove(booking)
        bookingList.add(booking)
        userBookingMap[booking.bookingByUser.userId] = bookingList
    }

    override fun getBooking(bookingId: String): Booking {
        return getAllUserBookings().find { it.bookingId == bookingId }
            ?: throw NotFoundException("Booking not found with id $bookingId")
    }

    override fun getConfirmedBooking(bookingId: String): List<Booking> {
        TODO("Not yet implemented")
    }

    override fun getAllUserBookings(): List<Booking> {
        return userBookingMap.values.flatten()
    }

    override fun getUserBookings(userId: String): List<Booking> {
        return userBookingMap[userId] ?: throw NotFoundException("User not found with id $userId")
    }

    private fun isAnySeatAlreadyBooked(show: Show, seatsToCheck: List<Seat>): Boolean {
        val allBookings: List<Booking> =
            getAllUserBookings() // Assuming this returns all bookings in the system

        // Filter bookings for the specific show
        val bookingsForThisShow = allBookings.filter { booking ->
            booking.bookingShow.showId == show.showId
        }

        // Get all seats that have been booked for this show across all relevant bookings
        val allBookedSeatsForShow: List<Seat> = bookingsForThisShow.flatMap { it.bookedSeats }

        // Check if any of the seatsToCheck are present in the allBookedSeatsForShow
        // This requires Seat to have a proper equals() and hashCode() implementation
        for (seat in seatsToCheck) {
            if (allBookedSeatsForShow.contains(seat)) {
                return true // Found a seat that is already booked
            }
        }

        return false // None of the seatsToCheck are currently booked for this show
    }

    override fun confirmBooking(show: Show, user: User): Booking? {
        val userBookingList = getUserBookings(user.userId)
        val showBooking =
            userBookingList.find { booking -> booking.bookingShow.showId == show.showId }
        showBooking?.bookedSeats?.forEach { seat ->
            seatLockProvider.lockSeatPermanent(seat = seat, show, user)
            seat.seatStatus = SeatStatusType.CONFIRM
        }

        return showBooking
    }


}