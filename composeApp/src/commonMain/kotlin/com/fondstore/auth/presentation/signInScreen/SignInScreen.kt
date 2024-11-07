package com.fondstore.auth.presentation.signInScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.fondstore.auth.presentation.authScreen.AuthScreen
import com.fondstore.auth.presentation.forgotPasswordScreen.ForgotPasswordScreen
import com.fondstore.auth.presentation.signUpScreen.SignUpScreen
import com.fondstore.voyager.NavigationKey
import com.fondstore.voyager.pop
import com.fondstore.voyager.push

class SignInScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SignInScreenModel>()
        val state by screenModel.state.collectAsState()

        if (!state.isActive) {
            pop(
                navigator = LocalNavigator.current?.parent,
                navigationKey = NavigationKey.Klass(AuthScreen::class)
            )
        }

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                SignInScreenDestination.SignUpScreen -> SignUpScreen()
                SignInScreenDestination.ForgotPasswordScreen -> ForgotPasswordScreen()
            }

            push(
                screen = screen,
                onNavigation = {
                    screenModel.onEvent(SignInScreenEvent.ClearDestination)
                    screenModel.onEvent(SignInScreenEvent.ClearScreen)
                }
            )
        }

        SignInScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}