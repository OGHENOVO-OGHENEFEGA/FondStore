package com.fondstore.notification.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.voyager.pop

class NotificationsScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<NotificationsScreenModel>()
        val state by screenModel.state.collectAsState()

        if (!state.isActive) {
            pop()
        }

        NotificationsScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}