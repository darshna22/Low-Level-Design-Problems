package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.repository.MovieRepository
import java.util.*


class MovieRepositoryImpl: MovieRepository {
    private var movieMap = mutableMapOf<String, Movie>()


    override fun getMovie(movieId: String?): Movie? {
        if (!movieMap.containsKey(movieId)) {
            throw NotFoundException("Movie not found with id $movieId")
        }
        return movieMap[movieId]
    }

    override fun createMovie(movieName: String): Movie {
        val movieId = UUID.randomUUID().toString()
        val movie = Movie(movieId, movieName)
        movieMap[movieId] = movie
        return movie
    }
}