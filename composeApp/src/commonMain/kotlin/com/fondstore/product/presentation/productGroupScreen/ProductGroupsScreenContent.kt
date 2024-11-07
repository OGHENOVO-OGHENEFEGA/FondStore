package com.fondstore.product.presentation.productGroupScreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.screenBackground
import com.fondstore.favourites.domain.models.FavouritesState
import com.fondstore.product.domain.models.ProductGroup
import com.fondstore.product.domain.models.ProductsState
import com.fondstore.product.presentation.components.ProductGroupButton
import com.fondstore.product.presentation.components.ProductLazyColumn
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductGroupsScreenContent(
    state: ProductGroupsScreenState,
    productsState: ProductsState,
    favouritesState: FavouritesState,
    onEvent: (ProductGroupsScreenEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
                    .padding(horizontal = 10.dp).padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    ProductGroup.entries.forEach { group ->
                        ProductGroupButton(
                            group = group,
                            isSelected = group == state.selectedGroup,
                            modifier = Modifier.widthIn(min = 100.dp).height(40.dp),
                            onClick = {
                                onEvent(ProductGroupsScreenEvent.SelectGroup(group))
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        val pagerState = rememberPagerState { ProductGroup.entries.size }

        LaunchedEffect(state.selectedGroup) {
            val index = ProductGroup.entries.indexOf(state.selectedGroup)
            pagerState.scrollToPage(index)
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize().screenBackground().padding(innerPadding),
            contentPadding = PaddingValues(10.dp),
            pageSpacing = 10.dp,
            userScrollEnabled = false
        ) { index ->
            val group = ProductGroup.entries[index]

            val products = when (group) {
                ProductGroup.EXPLORE -> productsState.exploreProductsResult?.dataOrNull
                    ?: listOf()

                ProductGroup.BEST_DEALS -> productsState.bestDealsResult?.dataOrNull ?: listOf()
                ProductGroup.POPULAR -> productsState.popularProductsResult?.dataOrNull
                    ?: listOf()

                ProductGroup.NEW_ARRIVALS -> productsState.newArrivalsResult?.dataOrNull
                    ?: listOf()
            }

            val isLoading = when (group) {
                ProductGroup.EXPLORE -> productsState.isGettingExploreProducts
                ProductGroup.BEST_DEALS -> productsState.isGettingBestDeals
                ProductGroup.POPULAR -> productsState.isGettingPopularProducts
                ProductGroup.NEW_ARRIVALS -> productsState.isGettingNewArrivals
            }

            ProductLazyColumn(
                isLoadingProducts = isLoading,
                products = products,
                favourites = favouritesState.result?.dataOrNull ?: listOf(),
                favouritesRequestLoadingList = favouritesState.requestLoadingList,
                onToggleProductFavouriteState = { product ->
                    onEvent(ProductGroupsScreenEvent.ToggleProductFavouriteState(product))
                },
                modifier = Modifier.fillMaxSize(),
                header = {
                    Text(
                        text = stringResource(state.selectedGroup.description),
                        color = MaterialTheme.appColors.color100,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                },
                onProductSelected = { product ->
                    onEvent(
                        ProductGroupsScreenEvent.Navigate(
                            ProductGroupsScreenDestination.ProductScreen(
                                product = product
                            )
                        )
                    )
                }
            )
        }
    }
}