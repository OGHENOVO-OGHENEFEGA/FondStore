package com.fondstore.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.fondstore.category.categoriesScreen.CategoriesScreen
import com.fondstore.app.AppScreenModel
import com.fondstore.category.categoryScreen.CategoryScreen
import com.fondstore.category.trendingCategoryScreen.TrendingCategoryScreen
import com.fondstore.product.presentation.productGroupScreen.ProductGroupsScreen
import com.fondstore.store.StoreScreen
import com.fondstore.voyager.NavigationKey
import com.fondstore.voyager.koinParentScreenModel
import com.fondstore.voyager.koinRootScreenModel
import com.fondstore.voyager.push
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.home
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

object HomeScreenTab : Tab {

    override val options: TabOptions
        @Composable get() {
            return TabOptions(index = 0u, title = stringResource(Res.string.home))
        }

    @Composable
    override fun Content() {
        val screenModel = koinParentScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                HomeScreenDestination.AuthScreen -> TODO()

                is HomeScreenDestination.ProductScreen -> TODO()

                is HomeScreenDestination.ProductGroupsScreen -> {
                    ProductGroupsScreen(group = destination.group)
                }

                is HomeScreenDestination.CategoryScreen -> {
                    CategoryScreen(encodedCategory = Json.encodeToString(destination.category))
                }

                is HomeScreenDestination.CategoriesScreen -> {
                    CategoriesScreen(encodedCategories = Json.encodeToString(destination.categories))
                }

                is HomeScreenDestination.TrendingCategoryScreen -> {
                    TrendingCategoryScreen(encodedCategory = Json.encodeToString(destination.category))
                }
            }

            push(
                screen = screen,
                navigator = LocalNavigator.current?.parent,
                navigationKey = NavigationKey.Klass(StoreScreen::class),
                onNavigation = {
                    screenModel.onEvent(HomeScreenEvent.ClearDestination)
                }
            )
        }

        val appScreenModel = koinRootScreenModel<AppScreenModel>()
        val appState by appScreenModel.state.collectAsState()

        HomeScreenContent(
            productsState = appState.productsState,
            favouritesState = appState.favouritesState,
            onEvent = screenModel::onEvent
        )
    }
}