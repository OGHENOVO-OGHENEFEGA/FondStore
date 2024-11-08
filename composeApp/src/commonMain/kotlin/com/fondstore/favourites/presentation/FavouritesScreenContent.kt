package com.fondstore.favourites.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.NoContent
import com.fondstore.core.presentation.screenBackground
import com.fondstore.error.Result
import com.fondstore.favourites.domain.models.FavouritesState
import com.fondstore.favourites.presentation.components.FavouriteProduct
import com.fondstore.product.domain.models.Product
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.no_favourites_action_message
import fondstore.composeapp.generated.resources.no_favourites_heading
import fondstore.composeapp.generated.resources.no_favourites_message
import org.jetbrains.compose.resources.stringResource

@Composable
fun FavouritesScreenContent(
    favouritesState: FavouritesState,
    onEvent: (FavouritesScreenEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize().screenBackground()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(favouritesState.result?.dataOrNull ?: listOf()) { product ->
                FavouriteProduct(
                    product = product,
                    isFavouriteRequestLoading =
                    favouritesState.requestLoadingList.map(Product::id).contains(product.id),
                    modifier = Modifier.fillMaxWidth().height(98.dp),
                    onRemoveProduct = {
                        onEvent(FavouritesScreenEvent.RemoveProduct(product))
                    },
                    onClick = {
                        onEvent(
                            FavouritesScreenEvent.Navigate(
                                FavouritesScreenDestination.ProductScreen(product = product)
                            )
                        )
                    }
                )
            }
        }

        if (favouritesState.isGettingFavourites && favouritesState.result !is Result.Success) {
            LoadingAnimationBox()
        } else if (favouritesState.result?.dataOrNull.isNullOrEmpty()) {
            NoContent(
                heading = stringResource(Res.string.no_favourites_heading),
                message = stringResource(Res.string.no_favourites_message),
                actionMessage = stringResource(Res.string.no_favourites_action_message),
                onAction = {
                    onEvent(FavouritesScreenEvent.Navigate(FavouritesScreenDestination.HomeScreen))
                }
            )
        }
    }
}