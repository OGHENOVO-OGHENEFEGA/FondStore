package com.fondstore.category.categoriesScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.fondstore.category.categoriesScreen.CategoriesScreenContent
import com.fondstore.category.categoriesScreen.CategoriesScreenDestination
import com.fondstore.category.categoriesScreen.CategoriesScreenEvent
import com.fondstore.category.categoriesScreen.CategoriesScreenModel
import com.fondstore.category.categoryScreen.CategoryScreen
import com.fondstore.product.domain.models.Category
import com.fondstore.voyager.pop
import com.fondstore.voyager.push
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.parameter.parametersOf

data class CategoriesScreen(private val encodedCategories: String) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CategoriesScreenModel> {
            parametersOf(Json.decodeFromString<List<Category>>(encodedCategories))
        }

        val state by screenModel.state.collectAsState()

        if (!state.isActive) {
            pop()
        }

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                is CategoriesScreenDestination.CategoryScreen -> {
                    CategoryScreen(encodedCategory = Json.encodeToString(destination.category))
                }
            }

            push(
                screen = screen,
                onNavigation = {
                    screenModel.onEvent(CategoriesScreenEvent.ClearDestination)
                }
            )
        }

        CategoriesScreenContent(state = state, onEvent = screenModel::onEvent)
    }
}