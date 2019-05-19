package com.app.upcomingmovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.upcomingmovies.network.ApiInterface.Companion.service
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.MovieImage
import com.app.upcomingmovies.response.MovieImagesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MovieDetailViewModel(movieId: Long) : ViewModel() {
    private val _movieImages = MutableLiveData<List<MovieImage>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()
    private val _movie = MutableLiveData<Movie>()

    val movie: LiveData<Movie>
        get() = _movie

    val movieImages: LiveData<List<MovieImage>>
        get() = _movieImages

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val error: LiveData<String>
        get() = _error

    init {
        fetchImages(movieId)
    }

    private fun fetchImages(movieId: Long) {
        _isLoading.value = true
        service.getImagesByMovieId(movieId).enqueue(object : Callback<MovieImagesResponse> {
            override fun onResponse(call: Call<MovieImagesResponse>, response: Response<MovieImagesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _movieImages.value = it.backdrops
                        fetchMovieDetail(movieId)
                    }
                } else {
                    _error.value = "Failed to fetch movie detail"
                }
            }

            override fun onFailure(call: Call<MovieImagesResponse>, t: Throwable) {
                Timber.e(t, "Failed to fetch movie detail")
                _error.value = "Failed to fetch movie detail"
                _isLoading.value = false
            }
        })
    }

    private fun fetchMovieDetail(movieId: Long) {
        service.getMovieDetail(movieId).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        _movie.value = it
                    }
                } else {
                    _error.value = "Failed to fetch movie detail"
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                _isLoading.value = false
                _error.value = "Failed to fetch movie detail"
                Timber.e(t, "Failed to fetch movie detail")
            }
        })
    }
}