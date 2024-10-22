package com.fondstore.search.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.voyager.presentation.pop

class SearchScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SearchScreenModel>()
        val state by screenModel.state.collectAsState()

        if (!state.isActive) {
            pop()
        }

        val destination = state.destination

        if (destination != null) {

        }

        SearchScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}