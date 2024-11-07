package com.fondstore.category.trendingCategoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.screenPadding
import com.fondstore.favourites.domain.models.FavouritesState
import com.fondstore.product.presentation.components.ProductLazyColumn
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res

@Composable
fun TrendingCategoryScreenContent(
    state: TrendingCategoryScreenState,
    favouritesState: FavouritesState,
    onEvent: (TrendingCategoryScreenEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().screenPadding(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = state.category.name,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 20.sp
        )

        ProductLazyColumn(
            isLoadingProducts = state.isGettingItems,
            products = state.result?.dataOrNull?.results ?: listOf(),
            favourites = favouritesState.result?.dataOrNull ?: listOf(),
            favouritesRequestLoadingList = favouritesState.requestLoadingList,
            onToggleProductFavouriteState = { id ->
                onEvent(TrendingCategoryScreenEvent.ToggleProductFavouriteState(id))
            },
            modifier = Modifier.fillMaxSize(),
            isLoadingMoreProducts = state.isGettingNextItems,
            onLoadMoreProducts = {
                onEvent(TrendingCategoryScreenEvent.GetNextItems)
            },
            onProductSelected = { product ->
                onEvent(
                    TrendingCategoryScreenEvent.Navigate(
                        TrendingCategoryScreenDestination.ProductScreen(product)
                    )
                )
            }
        )
    }
}
