package com.app.upcomingmovies.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.upcomingmovies.ui.detail.MovieDetailViewModel

class ViewModelFactory(val id: Long) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(id) as T
    }
}