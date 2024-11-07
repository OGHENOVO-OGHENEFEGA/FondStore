package com.fondstore.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.fondstore.auth.presentation.authScreen.AuthScreen
import com.fondstore.store.StoreScreen
import com.fondstore.voyager.NavigationKey
import com.fondstore.voyager.koinNavigatorScreenModel
import com.fondstore.voyager.push

class ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current?.parent?.parent

        val screenModel = koinNavigatorScreenModel<ProfileScreenModel>(navigator = navigator)
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                ProfileScreenDestination.AuthScreen -> AuthScreen()
            }

            push(
                screen = screen,
                navigator = navigator,
                navigationKey = NavigationKey.Klass(StoreScreen::class),
                onNavigation = {
                    screenModel.onEvent(ProfileScreenEvent.ClearDestination)
                }
            )
        }

        ProfileScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}