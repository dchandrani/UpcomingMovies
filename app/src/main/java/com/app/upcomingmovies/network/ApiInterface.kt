package com.app.upcomingmovies.network

import com.app.upcomingmovies.BuildConfig
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.MovieImagesResponse
import com.app.upcomingmovies.response.MovieResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiInterface {
    @GET("movie/upcoming")
    fun getMovies(): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Long): Call<Movie>

    @GET("movie/{movie_id}/images")
    fun getImagesByMovieId(@Path("movie_id") movieId: Long): Call<MovieImagesResponse>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = BuildConfig.ApiKey
        private const val REQUEST_TIMEOUT = 60L

        private val client: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()

        val service: ApiInterface = client.create(ApiInterface::class.java)

        private fun getClient(): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

                okHttpClient.addInterceptor(interceptor)
            }

            val authInterceptor = Interceptor { chain ->
                val request = chain.request()
                val httpURL = request.url().newBuilder().addQueryParameter("api_key", API_KEY).build()
                chain.proceed(request.newBuilder().url(httpURL).build())
            }

            okHttpClient.addInterceptor(authInterceptor)

            return okHttpClient.build()
        }
    }
}