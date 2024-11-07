package com.fondstore.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.fondstore.auth.presentation.authScreen.AuthScreen
import com.fondstore.launcher.presentation.LauncherScreen
import com.fondstore.voyager.PlatformNavigatorContent

@Composable
fun AppScreenContent(state: AppState, onEvent: (AppEvent) -> Unit) {
    Navigator(LauncherScreen()) { navigator ->
        val destination = state.destination

        if (destination != null && navigator.lastItem !is AuthScreen) {
            navigator.push(AuthScreen())
            onEvent(AppEvent.ClearDestination)
        }

        PlatformNavigatorContent(navigator)
    }
}