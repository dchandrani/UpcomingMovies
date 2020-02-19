package com.app.upcomingmovies.data

import com.app.upcomingmovies.database.MoviesDao
import com.app.upcomingmovies.network.ApiInterface
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.MovieImage
import com.app.upcomingmovies.response.Response

class Repository(private val api: ApiInterface, private val moviesDao: MoviesDao) {

    suspend fun getMovies(): Response<List<Movie>> {
        return try {
            // Check with the db if movies exist fetch from it
            var movies = moviesDao.getMovies()
            if (movies.isEmpty()) {
                movies = api.getMoviesAsync().results
                moviesDao.insertMovies(movies)
                println("fetching from api")
                Response.Success(movies)
            } else {
                println("fetching from db")
                Response.Success(movies)
            }
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }

    suspend fun getMovieById(id: Long): Response<Movie> {
        return try {
            val movie = api.getMovieDetailAsync(id)
            Response.Success(movie)
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }

    suspend fun getImagesByMovieId(id: Long): Response<List<MovieImage>> {
        return try {
            val movieImages = api.getImagesByMovieIdAsync(id)
            Response.Success(movieImages.backdrops)
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }
}