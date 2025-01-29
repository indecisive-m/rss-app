package com.example.rss_app.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RssApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
