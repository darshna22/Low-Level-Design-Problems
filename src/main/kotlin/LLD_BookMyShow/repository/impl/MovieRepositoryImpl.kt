package LLD_BookMyShow.repository.impl

import LLD_BookMyShow.exception.AlreadyExistsException
import LLD_BookMyShow.exception.NotFoundException
import LLD_BookMyShow.model.Movie
import LLD_BookMyShow.repository.MovieRepository
import java.util.*


class MovieRepositoryImpl : MovieRepository {
    private var movieMap = mutableMapOf<String, Movie>()

    override fun createMovie(movieName: String) {
        if (movieMap.containsKey(movieName)) {
            throw AlreadyExistsException("Movie already exists with name $movieName")
        }
        val movie = Movie(movieId = UUID.randomUUID().toString(), movieName = movieName)
        movieMap[movie.movieId] = movie
    }

    override fun getMovie(movieId: String): Movie {
        return movieMap[movieId] ?: throw NotFoundException("Movie not found with id $movieId")
    }


    override fun getAllMovie(): List<Movie> {
        return movieMap.values.toList()
    }
}