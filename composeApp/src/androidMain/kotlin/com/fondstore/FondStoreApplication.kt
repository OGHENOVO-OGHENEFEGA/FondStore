package com.fondstore

import android.app.Application
import com.fondstore.di.KoinManager

class FondStoreApplication : Application() {
    init {
        KoinManager(this).start()
    }
}