package com.app.upcomingmovies

import android.annotation.SuppressLint
import android.app.Application
import com.app.upcomingmovies.di.databaseModule
import com.app.upcomingmovies.di.networkModule
import com.app.upcomingmovies.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

@SuppressLint("Registered")
class UpcomingMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UpcomingMoviesApp)
            modules(listOf(networkModule, viewModelModule, databaseModule))
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}