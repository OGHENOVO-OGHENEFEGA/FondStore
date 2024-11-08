package com.fondstore.cart.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface CartResponse {
    @Serializable
    data class Success(
        @SerialName("cart_items")
        val cartItems: List<CartItemResponse> = listOf(),
        @SerialName("id")
        val id: String = "",
        @SerialName("price_grand_total")
        val priceGrandTotal: Double = 0.0,
        @SerialName("total_quantity")
        val totalQuantity: Int = 0,
    ) : CartResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = "",
    ) : CartResponse
}