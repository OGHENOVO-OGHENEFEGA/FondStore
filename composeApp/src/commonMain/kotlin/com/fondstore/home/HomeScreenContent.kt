package com.fondstore.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fondstore.core.presentation.screenBackground
import com.fondstore.core.presentation.screenPadding
import com.fondstore.favourites.domain.models.FavouritesState
import com.fondstore.product.domain.models.ProductGroup
import com.fondstore.product.domain.models.ProductsState
import com.fondstore.product.presentation.components.CategoriesShelf
import com.fondstore.product.presentation.components.ProductsShelf
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.best_deals
import fondstore.composeapp.generated.resources.best_deals_description
import fondstore.composeapp.generated.resources.categories
import fondstore.composeapp.generated.resources.categories_description
import fondstore.composeapp.generated.resources.explore
import fondstore.composeapp.generated.resources.explore_description
import fondstore.composeapp.generated.resources.new_arrivals
import fondstore.composeapp.generated.resources.new_arrivals_description
import fondstore.composeapp.generated.resources.popular
import fondstore.composeapp.generated.resources.popular_description
import fondstore.composeapp.generated.resources.trending
import fondstore.composeapp.generated.resources.trending_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreenContent(
    productsState: ProductsState,
    favouritesState: FavouritesState,
    onEvent: (HomeScreenEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().screenBackground().verticalScroll(rememberScrollState())
            .screenPadding()
    ) {
        Column(
            modifier = Modifier.padding(bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            ProductsShelf(
                name = stringResource(Res.string.explore),
                description = stringResource(Res.string.explore_description),
                isGettingProducts = productsState.isGettingExploreProducts,
                products = productsState.exploreProductsResult?.dataOrNull ?: listOf(),
                favourites = favouritesState.result?.dataOrNull ?: listOf(),
                favouritesRequestLoadingList = favouritesState.requestLoadingList,
                onToggleProductFavouritesState = { product ->
                    onEvent(HomeScreenEvent.ToggleProductFavouriteState(product))
                },
                onViewAll = {
                    onEvent(
                        HomeScreenEvent.Navigate(
                            HomeScreenDestination.ProductGroupsScreen(ProductGroup.EXPLORE)
                        )
                    )
                },
                onProductSelected = { product ->
                    onEvent(HomeScreenEvent.Navigate(HomeScreenDestination.ProductScreen(product)))
                }
            )

            val categories = productsState.categoriesResult?.dataOrNull ?: listOf()

            CategoriesShelf(
                name = stringResource(Res.string.categories),
                description = stringResource(Res.string.categories_description),
                isGettingCategories = productsState.isGettingCategories,
                categories = categories,
                onViewAll = {
                    onEvent(
                        HomeScreenEvent.Navigate(
                            HomeScreenDestination.CategoriesScreen(
                                categories = categories
                            )
                        )
                    )
                },
                onCategorySelected = { category ->
                    onEvent(HomeScreenEvent.Navigate(HomeScreenDestination.CategoryScreen(category)))
                }
            )

            ProductsShelf(
                name = stringResource(Res.string.best_deals),
                description = stringResource(Res.string.best_deals_description),
                isGettingProducts = productsState.isGettingBestDeals,
                products = productsState.bestDealsResult?.dataOrNull ?: listOf(),
                favourites = favouritesState.result?.dataOrNull ?: listOf(),
                favouritesRequestLoadingList = favouritesState.requestLoadingList,
                onToggleProductFavouritesState = { product ->
                    onEvent(HomeScreenEvent.ToggleProductFavouriteState(product))
                },
                onViewAll = {
                    onEvent(
                        HomeScreenEvent.Navigate(
                            HomeScreenDestination.ProductGroupsScreen(ProductGroup.BEST_DEALS)
                        )
                    )
                },
                onProductSelected = { product ->
                    onEvent(HomeScreenEvent.Navigate(HomeScreenDestination.ProductScreen(product)))
                }
            )

            CategoriesShelf(
                name = stringResource(Res.string.trending),
                description = stringResource(Res.string.trending_description),
                isGettingCategories = productsState.isGettingTrendingCategories,
                categories = productsState.trendingCategoriesResult?.dataOrNull ?: listOf(),
                onCategorySelected = { category ->
                    onEvent(
                        HomeScreenEvent.Navigate(
                            HomeScreenDestination.TrendingCategoryScreen(category)
                        )
                    )
                }
            )

            ProductsShelf(
                name = stringResource(Res.string.popular),
                description = stringResource(Res.string.popular_description),
                isGettingProducts = productsState.isGettingPopularProducts,
                products = productsState.popularProductsResult?.dataOrNull ?: listOf(),
                favourites = favouritesState.result?.dataOrNull ?: listOf(),
                favouritesRequestLoadingList = favouritesState.requestLoadingList,
                onToggleProductFavouritesState = { product ->
                    onEvent(HomeScreenEvent.ToggleProductFavouriteState(product))
                },
                onViewAll = {
                    onEvent(
                        HomeScreenEvent.Navigate(
                            HomeScreenDestination.ProductGroupsScreen(ProductGroup.POPULAR)
                        )
                    )
                },
                onProductSelected = { product ->
                    onEvent(HomeScreenEvent.Navigate(HomeScreenDestination.ProductScreen(product)))
                }
            )

            ProductsShelf(
                name = stringResource(Res.string.new_arrivals),
                description = stringResource(Res.string.new_arrivals_description),
                isGettingProducts = productsState.isGettingNewArrivals,
                products = productsState.newArrivalsResult?.dataOrNull ?: listOf(),
                favourites = favouritesState.result?.dataOrNull ?: listOf(),
                favouritesRequestLoadingList = favouritesState.requestLoadingList,
                onToggleProductFavouritesState = { product ->
                    onEvent(HomeScreenEvent.ToggleProductFavouriteState(product))
                },
                onViewAll = {
                    onEvent(
                        HomeScreenEvent.Navigate(
                            HomeScreenDestination.ProductGroupsScreen(ProductGroup.NEW_ARRIVALS)
                        )
                    )
                },
                onProductSelected = { product ->
                    onEvent(HomeScreenEvent.Navigate(HomeScreenDestination.ProductScreen(product)))
                }
            )
        }
    }
}