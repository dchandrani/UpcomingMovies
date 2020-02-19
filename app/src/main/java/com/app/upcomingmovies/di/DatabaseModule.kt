package com.app.upcomingmovies.di

import com.app.upcomingmovies.database.MoviesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { MoviesDatabase.getDatabase(androidContext()).getMoviesDao() }
}