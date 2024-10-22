package com.fondstore.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import com.fondstore.voyager.presentation.koinRootScreenModel

class AppScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinRootScreenModel<AppScreenModel>()
        val state by screenModel.state.collectAsState()

        AppScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}