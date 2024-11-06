package com.fondstore.di

import com.fondstore.helpCentre.utils.HelpCentreManager
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { Darwin.create() }
    factory { HelpCentreManager() }
}