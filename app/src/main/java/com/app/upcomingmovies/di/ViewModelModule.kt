package com.app.upcomingmovies.di

import com.app.upcomingmovies.ui.detail.MovieDetailViewModel
import com.app.upcomingmovies.ui.list.MovieListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }

    viewModel { MovieDetailViewModel(get()) }
}