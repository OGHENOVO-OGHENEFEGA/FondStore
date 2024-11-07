package com.fondstore.category.categoryScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.app.AppEvent
import com.fondstore.app.AppScreenModel
import com.fondstore.auth.presentation.authScreen.AuthScreen
import com.fondstore.home.HomeScreenEvent
import com.fondstore.product.domain.models.Category
import com.fondstore.voyager.koinRootScreenModel
import com.fondstore.voyager.push
import kotlinx.serialization.json.Json
import org.koin.core.parameter.parametersOf


data class CategoryScreen(private val encodedCategory: String) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CategoryScreenModel> {
            parametersOf(Json.decodeFromString<Category>(encodedCategory))
        }

        val state by screenModel.state.collectAsState()

//        val destination = state.destination
//
//        if (destination != null) {
//            val screen = when (destination) {
//                is CategoryScreenDestination.ProductScreen -> TODO()
//            }
//
//            push(
//                screen = screen,
//                onNavigation = {
//                    screenModel.onEvent(CategoryScreenEvent.ClearDestination)
//                }
//            )
//        }

        val appScreenModel = koinRootScreenModel<AppScreenModel>()
        val appState by appScreenModel.state.collectAsState()

        CategoryScreenContent(
            state = state,
            favouritesState = appState.favouritesState,
            onEvent = { event ->
                when (event) {
                    is CategoryScreenEvent.ToggleProductFavouriteState -> {
                        appScreenModel.onEvent(AppEvent.ToggleProductFavouriteState(event.product))
                    }

                    else -> screenModel.onEvent(event)
                }
            }
        )
    }
}