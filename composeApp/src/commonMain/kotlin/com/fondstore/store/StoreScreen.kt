package com.fondstore.store

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.lifecycle.LifecycleEffectOnce
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.app.AppEvent
import com.fondstore.app.AppScreenModel
import com.fondstore.notification.presentation.NotificationsScreen
import com.fondstore.search.SearchScreen
import com.fondstore.voyager.koinRootScreenModel
import com.fondstore.voyager.push

class StoreScreen : Screen {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val appScreenModel = koinRootScreenModel<AppScreenModel>()

        LifecycleEffectOnce {
            appScreenModel.onEvent(AppEvent.GetProducts)
        }

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