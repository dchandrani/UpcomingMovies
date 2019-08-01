package com.app.upcomingmovies.data

import com.app.upcomingmovies.network.ApiInterface
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.MovieImage
import com.app.upcomingmovies.response.Response

class Repository(private val api: ApiInterface) {

    suspend fun getMovies(): Response<List<Movie>> {
        return try {
            val movies = api.getMoviesAsync().results
            Response.Success(movies)
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