package com.fondstore.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.app.AppEvent
import com.fondstore.app.AppScreenModel
import com.fondstore.voyager.koinRootScreenModel
import com.fondstore.voyager.pop

class SearchScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SearchScreenModel>()
        val state by screenModel.state.collectAsState()

        if (!state.isActive) {
            pop()
        }

//        val destination = state.destination
//
//        if (destination != null) {
//            val screen = when (destination) {
//                is SearchScreenDestination.ProductScreen -> TODO()
//            }
//
//            push(
//                screen = screen,
//                onNavigation = {
//                    screenModel.onEvent(SearchScreenEvent.ClearDestination)
//                }
//            )
//        }

        val appScreenModel = koinRootScreenModel<AppScreenModel>()
        val appState by appScreenModel.state.collectAsState()

        SearchScreenContent(
            state = state,
            favouritesState = appState.favouritesState,
            onEvent = { event ->
                when (event) {
                    is SearchScreenEvent.ToggleProductFavouriteState -> {
                        appScreenModel.onEvent(AppEvent.ToggleProductFavouriteState(event.product))
                    }

                    else -> screenModel.onEvent(event)
                }
            }
        )
    }
}