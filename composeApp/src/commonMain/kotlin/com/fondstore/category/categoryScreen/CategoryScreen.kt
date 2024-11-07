package com.fondstore.category.categoryScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.app.AppScreenModel
import com.fondstore.product.domain.models.Category
import com.fondstore.voyager.koinRootScreenModel
import kotlinx.serialization.json.Json
import org.koin.core.parameter.parametersOf


data class CategoryScreen(private val encodedCategory: String) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CategoryScreenModel> {
            parametersOf(Json.decodeFromString<Category>(encodedCategory))
        }

        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            screenModel.onEvent(CategoryScreenEvent.ClearDestination)
        }

        val appScreenModel = koinRootScreenModel<AppScreenModel>()
        val appState by appScreenModel.state.collectAsState()

        CategoryScreenContent(
            state = state,
            favouritesState = appState.favouritesState,
            onEvent = screenModel::onEvent
        )
    }
}