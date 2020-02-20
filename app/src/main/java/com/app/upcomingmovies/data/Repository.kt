package com.app.upcomingmovies.data

import com.app.upcomingmovies.database.MovieImagesDao
import com.app.upcomingmovies.database.MoviesDao
import com.app.upcomingmovies.network.ApiInterface
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.MovieImage
import com.app.upcomingmovies.response.Response

class Repository(
    private val api: ApiInterface,
    private val moviesDao: MoviesDao,
    private val movieImagesDao: MovieImagesDao
) {

    suspend fun getMovies(): Response<List<Movie>> {
        return try {
            // Check with the db if movies exist fetch from it
            var movies = moviesDao.getMovies()
            if (movies.isEmpty()) {
                movies = api.getMoviesAsync().results
                moviesDao.insertMovies(movies)
                Response.Success(movies)
            } else {
                Response.Success(movies)
            }
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }

    suspend fun getMovieById(id: Long): Response<Movie> {
        return try {
            val movie = moviesDao.getMovieById(id)
            Response.Success(movie)
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }

    suspend fun getImagesByMovieId(id: Long): Response<List<MovieImage>> {
        return try {
            // Fetch movie images from the db first
            var movieImages = movieImagesDao.getMovieImages(id)

            // If no images found fetch from the api and insert in db
            if (movieImages.isEmpty()) {
                movieImages = api.getImagesByMovieIdAsync(id).backdrops

                movieImages.map {
                    it.id = id
                }

                movieImagesDao.insertMovieImages(movieImages)
                Response.Success(movieImages)
            } else {
                Response.Success(movieImages)
            }
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }
}