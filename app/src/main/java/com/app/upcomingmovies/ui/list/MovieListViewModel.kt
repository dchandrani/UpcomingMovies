package com.app.upcomingmovies.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.upcomingmovies.data.Repository
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.Response
import com.app.upcomingmovies.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: Repository) : BaseViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>>
        get() = _movies

    init {
        isLoading.value = true

        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            when (val response = repository.getMovies()) {
                is Response.Success -> _movies.value = response.movies
                is Response.Error -> error.value = response.message
            }

            isLoading.value = false
        }
    }
}