package LLD_BookMyShow.services

import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.repository.MovieRepository

class MovieService(private val movieRepository: MovieRepository) {

    fun createMovie(movieName: String): Movie {
        return movieRepository.createMovie(movieName)
    }

    fun getMovie(movieId: String): Movie {
        return movieRepository.getMovie(movieId)
    }

    fun getAllMovie(): List<Movie> = movieRepository.getAllMovie()

}