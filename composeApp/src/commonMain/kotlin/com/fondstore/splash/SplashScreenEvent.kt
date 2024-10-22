package com.fondstore.splash

sealed interface SplashScreenEvent {
    data class Navigate(val destination: SplashScreenDestination) : SplashScreenEvent
}