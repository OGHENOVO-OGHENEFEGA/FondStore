package com.fondstore.favourites.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.fondstore.app.AppEvent
import com.fondstore.app.AppScreenModel
import com.fondstore.home.HomeScreenTab
import com.fondstore.voyager.koinRootScreenModel
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.favourites
import org.jetbrains.compose.resources.stringResource

object FavouritesScreenTab : Tab {

    override val options: TabOptions
        @Composable get() {
            return TabOptions(index = 1u, title = stringResource(Res.string.favourites))
        }

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<FavouritesScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                FavouritesScreenDestination.HomeScreen -> HomeScreenTab
                is FavouritesScreenDestination.ProductScreen -> null
            }

            if (screen is HomeScreenTab) {
                val navigator = LocalTabNavigator.current

                if (navigator.current != HomeScreenTab) {
                    navigator.current = HomeScreenTab
                    screenModel.onEvent(FavouritesScreenEvent.ClearDestination)
                }
            }
        }

        val appScreenModel = koinRootScreenModel<AppScreenModel>()
        val appState by appScreenModel.state.collectAsState()

        FavouritesScreenContent(
            favouritesState = appState.favouritesState,
            onEvent = { event ->
                when (event) {
                    is FavouritesScreenEvent.RemoveProduct -> {
                        appScreenModel.onEvent(AppEvent.RemoveProductFromFavourites(event.product))
                    }

                    else -> screenModel.onEvent(event)
                }
            }
        )
    }

}