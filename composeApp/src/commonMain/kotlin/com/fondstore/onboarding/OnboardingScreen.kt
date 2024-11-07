package com.fondstore.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.store.StoreScreen
import com.fondstore.voyager.replace

class OnboardingScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<OnboardingScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                OnboardingScreenDestination.StoreScreen -> StoreScreen()
            }

            replace(screen)
        }

        OnboardingScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}