package com.fondstore.splash

sealed interface SplashScreenDestination {
    data object OnboardingScreen : SplashScreenDestination
}