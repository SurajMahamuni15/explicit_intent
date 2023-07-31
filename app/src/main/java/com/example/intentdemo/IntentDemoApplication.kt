package com.example.intentdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IntentDemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}