package com.fondstore.launcher.presentation

sealed interface LauncherScreenDestination {
    data object SplashScreen: LauncherScreenDestination
    data object StoreScreen: LauncherScreenDestination
}