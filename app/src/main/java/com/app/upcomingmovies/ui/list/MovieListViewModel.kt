package com.app.upcomingmovies.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.upcomingmovies.network.ApiInterface.Companion.service
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MovieListViewModel: ViewModel(){
    private val _movies = MutableLiveData<List<Movie>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val movies: LiveData<List<Movie>>
        get() = _movies

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val error: LiveData<String>
        get() = _error

    init {
        _isLoading.value = true

        fetchMovies()
    }

    private fun fetchMovies() {
        service.getMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    response.body()?.let {
                        _movies.value = it.results
                    }
                } else {
                    _error.value = "Error fetching movies"
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = "Error fetching movies"
                Timber.e(t, "Error fetching movies")
            }
        })
    }
}