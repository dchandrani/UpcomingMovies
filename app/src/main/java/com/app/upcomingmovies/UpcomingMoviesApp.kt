package com.app.upcomingmovies

import android.app.Application
import timber.log.Timber

class UpcomingMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}