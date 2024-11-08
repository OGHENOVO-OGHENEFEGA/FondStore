package com.fondstore.cart.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.fondstore.app.AppScreenModel
import com.fondstore.home.HomeScreenTab
import com.fondstore.voyager.koinRootScreenModel
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.cart
import org.jetbrains.compose.resources.stringResource

object CartScreenTab : Tab {

    override val options: TabOptions
        @Composable get() {
            return TabOptions(index = 2u, title = stringResource(Res.string.cart))
        }

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CartScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                CartScreenDestination.HomeScreen -> HomeScreenTab
                is CartScreenDestination.ProductScreen -> null
                is CartScreenDestination.AddressScreen -> null
            }

            if (screen is HomeScreenTab) {
                val navigator = LocalTabNavigator.current

                if (navigator.current != HomeScreenTab) {
                    navigator.current = HomeScreenTab
                    screenModel.onEvent(CartScreenEvent.ClearDestination)
                }
            }
        }

        val appScreenModel = koinRootScreenModel<AppScreenModel>()
        val appState by appScreenModel.state.collectAsState()

        CartScreenContent(cartState = appState.cartState, onEvent = screenModel::onEvent)
    }
}