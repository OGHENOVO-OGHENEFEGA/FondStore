package com.fondstore.cart.data.remote.responses


import com.fondstore.product.data.remote.responses.ProductResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartItemResponse(
    @SerialName("cart")
    val cart: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("item")
    val item: ProductResponse.Success = ProductResponse.Success(),
    @SerialName("price_sub_total")
    val priceSubTotal: Double = 0.0,
    @SerialName("quantity")
    val quantity: Int = 0,
    @SerialName("size")
    val size: CartItemSizeResponse? = null
)