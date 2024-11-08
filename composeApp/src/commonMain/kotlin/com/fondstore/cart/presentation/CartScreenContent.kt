package com.fondstore.cart.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.cart.domain.models.CartState
import com.fondstore.cart.presentation.components.CartItem
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.LoadingButton
import com.fondstore.core.presentation.NoContent
import com.fondstore.core.presentation.button
import com.fondstore.core.presentation.screenBackground
import com.fondstore.error.Result
import com.fondstore.resources.fontFamilyResource
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.empty_cart_action_message
import fondstore.composeapp.generated.resources.empty_cart_heading
import fondstore.composeapp.generated.resources.empty_cart_message
import fondstore.composeapp.generated.resources.place_order
import org.jetbrains.compose.resources.stringResource

@Composable
fun CartScreenContent(cartState: CartState, onEvent: (CartScreenEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxSize().screenBackground()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().weight(1f),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(cartState.result?.dataOrNull?.cartItems ?: listOf()) { item ->
                    CartItem(
                        item = item,
                        modifier = Modifier.height(98.dp),
                        isCartRequestLoading =
                        cartState.requestLoadingList.contains(item.item.id),
                        onRemoveProduct = {
                            onEvent(
                                CartScreenEvent.RemoveProduct(
                                    product = item.item,
                                    sizeId = item.size
                                )
                            )
                        },
                        onClick = {
                            onEvent(
                                CartScreenEvent.Navigate(
                                    CartScreenDestination.ProductScreen(product = item.item)
                                )
                            )
                        }
                    )
                }
            }

            if (cartState.result is Result.Success && !cartState.result.data?.cartItems.isNullOrEmpty()) {
                LoadingButton(
                    isLoading = cartState.requestLoadingList.isNotEmpty(),
                    onClick = {
                        val cartId = cartState.result.data?.id

                        if (cartId != null) {
                            onEvent(
                                CartScreenEvent.Navigate(
                                    CartScreenDestination.AddressScreen(cartId = cartId)
                                )
                            )
                        }
                    },
                    enabled = cartState.requestLoadingList.isEmpty(),
                    modifier = Modifier.button().padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.place_order),
                        fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                        fontSize = 16.sp
                    )
                }
            }
        }

        if (cartState.isGettingCart && cartState.result !is Result.Success) {
            LoadingAnimationBox()
        } else if (cartState.result?.dataOrNull == null) {
            NoContent(
                heading = stringResource(Res.string.empty_cart_heading),
                message = stringResource(Res.string.empty_cart_message),
                actionMessage = stringResource(Res.string.empty_cart_action_message),
                onAction = {
                    onEvent(CartScreenEvent.Navigate(CartScreenDestination.HomeScreen))
                }
            )
        }
    }
}