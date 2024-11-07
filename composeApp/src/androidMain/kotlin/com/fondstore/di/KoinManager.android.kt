package com.fondstore.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI

actual class KoinManager(private val context: Context) {
    @OptIn(KoinExperimentalAPI::class)
    actual fun start() {
        KoinStartup.onKoinStartup {
            androidLogger()
            androidContext(context)
            modules(platformModule, coreModule, repositoryModule, screenModelModule)
        }
    }
}