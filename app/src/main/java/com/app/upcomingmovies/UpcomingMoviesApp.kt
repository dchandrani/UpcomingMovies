package com.app.upcomingmovies

import android.app.Application
import com.app.upcomingmovies.di.networkModule
import com.app.upcomingmovies.di.viewModelModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class UpcomingMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = this,
            modules = listOf(networkModule, viewModelModule)
        )

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}