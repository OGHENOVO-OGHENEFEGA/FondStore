package com.fondstore.helpCentre

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class HelpCentreScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HelpCenterScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {

        }

        HelpCentreScreenContent(onEvent = screenModel::onEvent)
    }
}