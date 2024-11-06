package com.fondstore.di

import com.fondstore.helpCentre.utils.HelpCentreManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { OkHttp.create() }
    factory { HelpCentreManager(androidContext()) }
}