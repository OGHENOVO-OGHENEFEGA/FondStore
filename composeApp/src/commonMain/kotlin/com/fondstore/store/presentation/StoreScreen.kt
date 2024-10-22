package com.fondstore.store.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.notification.presentation.NotificationsScreen
import com.fondstore.search.presentation.SearchScreen
import com.fondstore.voyager.presentation.push

class StoreScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<StoreScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                StoreScreenDestination.SearchScreen -> SearchScreen()
                StoreScreenDestination.NotificationsScreen -> NotificationsScreen()
            }

            push(
                screen = screen,
                onNavigation = {
                    screenModel.onEvent(StoreScreenEvent.ClearDestination)
                }
            )
        }

        StoreScreenContent(onEvent = screenModel::onEvent)
    }
}