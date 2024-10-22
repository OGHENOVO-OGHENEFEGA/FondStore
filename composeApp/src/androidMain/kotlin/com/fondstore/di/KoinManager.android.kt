package com.fondstore.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

actual class KoinManager(private val context: Context) {
    actual fun start() {
        startKoin {
            androidLogger()
            androidContext(context)
            modules(platformModule, coreModule, repositoryModule, screenModelModule)
        }
    }
}