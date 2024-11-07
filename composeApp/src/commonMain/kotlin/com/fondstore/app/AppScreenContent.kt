package com.fondstore.app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.fondstore.launcher.presentation.LauncherScreen
import com.fondstore.voyager.PlatformNavigatorContent

@Composable
fun AppScreenContent(state: AppState, onEvent: (AppEvent) -> Unit) {
    Navigator(LauncherScreen()) { navigator ->
        PlatformNavigatorContent(navigator)
    }
}