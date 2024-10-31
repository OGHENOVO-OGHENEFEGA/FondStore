package com.fondstore.faqs.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.voyager.presentation.pop

class FaqsScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<FaqsScreenModel>()
        val state by screenModel.state.collectAsState()

        if (!state.isActive) {
            pop()
        }

        FaqsScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}