package com.fondstore.category.trendingCategoryScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.app.AppEvent
import com.fondstore.app.AppScreenModel
import com.fondstore.product.domain.models.Category
import com.fondstore.voyager.koinRootScreenModel
import kotlinx.serialization.json.Json
import org.koin.core.parameter.parametersOf

data class TrendingCategoryScreen(private val encodedCategory: String) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<TrendingCategoryScreenModel> {
            parametersOf(Json.decodeFromString<Category>(encodedCategory))
        }

        val state by screenModel.state.collectAsState()

//        val destination = state.destination
//
//        if (destination != null) {
//            val screen = when (destination) {
//                is TrendingCategoryScreenDestination.ProductScreen -> TODO()
//            }
//
//            push(
//                screen = screen,
//                onNavigation = {
//                    screenModel.onEvent(TrendingCategoryScreenEvent.ClearDestination)
//                }
//            )
//        }

        val appScreenModel = koinRootScreenModel<AppScreenModel>()
        val appState by appScreenModel.state.collectAsState()

        TrendingCategoryScreenContent(
            state = state,
            favouritesState = appState.favouritesState,
            onEvent = { event ->
                when (event) {
                    is TrendingCategoryScreenEvent.ToggleProductFavouriteState -> {
                        appScreenModel.onEvent(AppEvent.ToggleProductFavouriteState(event.product))
                    }

                    else -> screenModel.onEvent(event)
                }
            }
        )
    }
}