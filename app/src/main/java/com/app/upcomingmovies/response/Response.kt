package com.app.upcomingmovies.response

sealed class Response<out T> {
    class Success<T>(val movies: T) : Response<T>()

    class Error<T>(val message: String?) : Response<T>()
}