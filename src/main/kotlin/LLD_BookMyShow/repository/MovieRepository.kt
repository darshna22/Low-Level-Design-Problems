package LLD_BookMyShow.repository

import LLD_BookMyShow.model.Movie


interface MovieRepository {
    fun createMovie(movieName: String)
    fun getMovie(movieId: String): Movie
    fun getAllMovie(): List<Movie>
}