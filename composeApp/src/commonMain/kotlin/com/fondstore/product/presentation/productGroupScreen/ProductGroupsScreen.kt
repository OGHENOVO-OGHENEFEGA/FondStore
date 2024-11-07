package com.fondstore.product.presentation.productGroupScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.app.AppEvent
import com.fondstore.app.AppScreenModel
import com.fondstore.product.domain.models.ProductGroup
import com.fondstore.voyager.koinRootScreenModel
import org.koin.core.parameter.parametersOf

data class ProductGroupsScreen(private val group: ProductGroup) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ProductGroupsScreenModel> {
            parametersOf(group)
        }

        val state by screenModel.state.collectAsState()

//        val destination = state.destination
//
//        if (destination != null) {
//            val screen = when (destination) {
//                is ProductGroupsScreenDestination.ProductScreen -> TODO()
//            }
//
//            push(
//                screen = screen,
//                onNavigation = {
//                    screenModel.onEvent(ProductGroupsScreenEvent.ClearDestination)
//                }
//            )
//        }

        val appScreenModel = koinRootScreenModel<AppScreenModel>()
        val appState by appScreenModel.state.collectAsState()

        ProductGroupsScreenContent(
            state = state,
            productsState = appState.productsState,
            favouritesState = appState.favouritesState,
            onEvent = { event ->
                when (event) {
                    is ProductGroupsScreenEvent.ToggleProductFavouriteState -> {
                        appScreenModel.onEvent(AppEvent.ToggleProductFavouriteState(event.product))
                    }

                    else -> screenModel.onEvent(event)
                }
            }
        )
    }
}