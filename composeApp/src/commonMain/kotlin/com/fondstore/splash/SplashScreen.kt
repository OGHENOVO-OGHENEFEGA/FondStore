package com.fondstore.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.onboarding.OnboardingScreen
import com.fondstore.voyager.replace

class SplashScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SplashScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                SplashScreenDestination.OnboardingScreen -> OnboardingScreen()
            }

            replace(screen)
        }

        SplashScreenContent(onEvent = screenModel::onEvent)
    }
}