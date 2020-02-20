package com.app.upcomingmovies.di

import com.app.upcomingmovies.database.MoviesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { MoviesDatabase.getDatabase(androidContext()) }

    single { get<MoviesDatabase>().getMoviesDao() }

    single { get<MoviesDatabase>().getMovieImagesDao() }
}