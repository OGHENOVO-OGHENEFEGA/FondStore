package com.fondstore

import android.app.Application
import com.fondstore.di.KoinManager

class FondStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinManager(this).start()
    }
}