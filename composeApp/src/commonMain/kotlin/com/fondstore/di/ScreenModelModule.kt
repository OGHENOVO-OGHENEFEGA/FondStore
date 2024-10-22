package com.fondstore.di

import com.fondstore.app.AppScreenModel
import com.fondstore.launcher.presentation.LauncherScreenModel
import com.fondstore.onboarding.OnboardingScreenModel
import com.fondstore.splash.SplashScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val screenModelModule = module {
    factoryOf(::AppScreenModel)
    factoryOf(::LauncherScreenModel)
    factoryOf(::SplashScreenModel)
    factoryOf(::OnboardingScreenModel)
}