package com.fondstore.auth.presentation.signUpScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.voyager.pop

class SignUpScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SignUpScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            when (destination) {
                SignUpScreenDestination.SignInScreen -> {
                    pop()
                }
            }
        }

        SignUpScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}