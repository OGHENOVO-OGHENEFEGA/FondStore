package com.fondstore.di

import org.koin.core.context.startKoin

actual class KoinManager {
    actual fun start() {
        startKoin {
            modules(platformModule, coreModule, repositoryModule, screenModelModule)
        }
    }
}