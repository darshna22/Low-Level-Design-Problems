package LLD_BookMyShow.repository

import LLD_BookMyShow.model.Movie


interface MovieRepository {
    fun getMovie(movieId: String?): Movie?
    fun createMovie(movieName: String): Movie
}