package com.app.upcomingmovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.upcomingmovies.data.Repository
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.MovieImage
import com.app.upcomingmovies.response.Response
import com.app.upcomingmovies.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: Repository) : BaseViewModel() {
    private val _movieImages = MutableLiveData<List<MovieImage>>()
    private val _movie = MutableLiveData<Movie>()

    val movie: LiveData<Movie>
        get() = _movie

    val movieImages: LiveData<List<MovieImage>>
        get() = _movieImages

    fun fetchImages(movieId: Long) {
        _isLoading.value = true

        viewModelScope.launch {
            when (val response = repository.getImagesByMovieId(movieId)) {
                is Response.Success -> {
                    _movieImages.value = response.movies
                    fetchMovieDetail(movieId)
                }
                is Response.Error -> {
                    _error.value = response.message
                    _isLoading.value = false
                }
            }
        }
    }

    private suspend fun fetchMovieDetail(movieId: Long) {
        when (val response = repository.getMovieById(movieId)) {
            is Response.Success -> _movie.value = response.movies
            is Response.Error -> _error.value = response.message
        }
        _isLoading.value = false
    }
}