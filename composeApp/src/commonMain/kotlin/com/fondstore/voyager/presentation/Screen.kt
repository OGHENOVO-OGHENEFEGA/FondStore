package com.fondstore.voyager.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import kotlin.reflect.KClass

@Composable
fun Screen.push(
    screen: Screen,
    navigator: Navigator? = LocalNavigator.current,
    navigationKey: NavigationKey = NavigationKey.Key(key),
    onNavigation: (() -> Unit) = {}
) {
    LaunchedEffect(navigationKey) {
        when (navigationKey) {
            is NavigationKey.Key -> {
                navigator?.push(screen)
                onNavigation()
            }

            is NavigationKey.Klass -> {
                val lastScreen = navigator?.lastItemOrNull

                if (lastScreen != null && lastScreen::class == navigationKey.klass) {
                    navigator.push(screen)
                    onNavigation()
                }
            }
        }
    }
}

@Composable
fun Screen.replace(
    screen: Screen,
    navigator: Navigator? = LocalNavigator.current,
    navigationKey: NavigationKey = NavigationKey.Key(key)
) {
    LaunchedEffect(navigationKey) {
        when (navigationKey) {
            is NavigationKey.Key -> {
                navigator?.replace(screen)
            }

            is NavigationKey.Klass -> {
                val lastScreen = navigator?.lastItemOrNull

                if (lastScreen != null && lastScreen::class == navigationKey.klass) {
                    navigator.replace(screen)
                }
            }
        }
    }
}

@Composable
fun Screen.replaceAll(
    screen: Screen,
    navigator: Navigator? = LocalNavigator.current,
    navigationKey: NavigationKey = NavigationKey.Key(key)
) {
    LaunchedEffect(navigationKey) {
        when (navigationKey) {
            is NavigationKey.Key -> {
                navigator?.replaceAll(screen)
            }

            is NavigationKey.Klass -> {
                val lastScreen = navigator?.lastItemOrNull

                if (lastScreen != null && lastScreen::class == navigationKey.klass) {
                    navigator.replaceAll(screen)
                }
            }
        }
    }
}

@Composable
fun Screen.pop(
    navigator: Navigator? = LocalNavigator.current,
    navigationKey: NavigationKey = NavigationKey.Key(key)
) {
    LaunchedEffect(navigationKey) {
        when (navigationKey) {
            is NavigationKey.Key -> {
                navigator?.pop()
            }

            is NavigationKey.Klass -> {
                val lastScreen = navigator?.lastItemOrNull

                if (lastScreen != null && lastScreen::class == navigationKey.klass) {
                    navigator.pop()
                }
            }
        }
    }
}