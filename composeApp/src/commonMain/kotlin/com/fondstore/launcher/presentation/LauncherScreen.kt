package com.fondstore.launcher.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.splash.SplashScreen
import com.fondstore.store.StoreScreen
import com.fondstore.voyager.presentation.replace

class LauncherScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<LauncherScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                LauncherScreenDestination.SplashScreen -> SplashScreen()
                LauncherScreenDestination.StoreScreen -> StoreScreen()
            }

            replace(screen)
        }

        LauncherScreenContent()
    }
}