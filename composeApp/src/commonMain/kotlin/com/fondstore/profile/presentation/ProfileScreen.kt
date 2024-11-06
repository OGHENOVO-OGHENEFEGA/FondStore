package com.fondstore.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ProfileScreenModel>()
        val state by screenModel.state.collectAsState()

        ProfileScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}