package com.fondstore.auth.presentation.forgotPasswordScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.voyager.pop

class ForgotPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ForgotPasswordScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            when (destination) {
                ForgotPasswordScreenDestination.SignInScreen -> {
                    pop()
                }
            }
        }

        ForgotPasswordScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}