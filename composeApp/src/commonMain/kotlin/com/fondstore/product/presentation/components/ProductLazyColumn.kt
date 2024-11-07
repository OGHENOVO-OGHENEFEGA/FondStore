package com.fondstore.product.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.product.domain.models.Product

@Composable
fun ProductLazyColumn(
    isLoadingProducts: Boolean,
    products: List<Product>,
    favourites: List<Product>,
    favouritesRequestLoadingList: List<Product>,
    onToggleProductFavouriteState: ((Product) -> Unit),
    modifier: Modifier = Modifier,
    header: (@Composable () -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(bottom = 10.dp),
    isLoadingMoreProducts: Boolean = false,
    onLoadMoreProducts: (() -> Unit) = {},
    onProductSelected: (Product) -> Unit,
) {
    Box(modifier = modifier) {
        if (isLoadingProducts) {
            LoadingAnimationBox()
        } else if (products.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                NoProducts()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = contentPadding
            ) {
                if (header != null) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        header()
                    }
                }

                itemsIndexed(products) { index, product ->
                    if (index == products.lastIndex && !isLoadingMoreProducts) {
                        onLoadMoreProducts()
                    }

                    ProductBox(
                        product = product,
                        modifier = Modifier.fillMaxWidth(),
                        isFavourite = favourites.map(Product::id).contains(product.id),
                        isFavouritesRequestLoading =
                        favouritesRequestLoadingList.map(Product::id).contains(product.id),
                        onFavouriteCardClicked = {
                            onToggleProductFavouriteState.invoke(product)
                        },
                        onProductClicked = {
                            onProductSelected(product)
                        }
                    )
                }

                if (isLoadingMoreProducts) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircleLoadingAnimation()
                        }
                    }
                }
            }
        }
    }
}
