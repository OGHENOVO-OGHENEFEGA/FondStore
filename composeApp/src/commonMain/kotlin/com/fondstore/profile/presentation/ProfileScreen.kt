package com.fondstore.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.fondstore.voyager.koinNavigatorScreenModel

class ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current?.parent?.parent

        val screenModel = koinNavigatorScreenModel<ProfileScreenModel>(navigator = navigator)
        val state by screenModel.state.collectAsState()

        ProfileScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}