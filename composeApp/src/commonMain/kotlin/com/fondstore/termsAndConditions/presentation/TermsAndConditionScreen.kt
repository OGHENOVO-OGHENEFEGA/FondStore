package com.fondstore.termsAndConditions.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.voyager.presentation.pop

class TermsAndConditionScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<TermsAndConditionScreenModel>()
        val state by screenModel.state.collectAsState()

        if (!state.isActive) {
            pop()
        }

        TermsAndConditionScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}