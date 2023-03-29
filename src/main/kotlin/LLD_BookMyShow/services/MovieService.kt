package LLD_BookMyShow.services

import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.repository.MovieRepository

class MovieService(private val movieRepository: MovieRepository) {
    fun getMovie(movieId: String?): Movie? {
        return movieRepository.getMovie(movieId)
    }

    fun createMovie(movieName: String): Movie {
        return movieRepository.createMovie(movieName)
    }


}