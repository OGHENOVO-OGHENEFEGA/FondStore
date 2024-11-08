package com.fondstore.cart.domain.models

import com.fondstore.cart.domain.errors.CartError
import com.fondstore.error.Result

data class CartState(
    val isGettingCart: Boolean = false,
    val requestLoadingList: List<String> = listOf(),
    val result: Result<Cart?, CartError>? = null
)
