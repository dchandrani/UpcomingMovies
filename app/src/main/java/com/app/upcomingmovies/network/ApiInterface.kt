package com.app.upcomingmovies.network

import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.MovieImagesResponse
import com.app.upcomingmovies.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("movie/upcoming")
    suspend fun getMoviesAsync(): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailAsync(@Path("movie_id") movieId: Long): Movie

    @GET("movie/{movie_id}/images")
    suspend fun getImagesByMovieIdAsync(@Path("movie_id") movieId: Long): MovieImagesResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val REQUEST_TIMEOUT = 60L
    }
}