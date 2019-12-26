package com.kucingselfie.kadesubmission

import android.app.Application
import timber.log.Timber

class FootballApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}