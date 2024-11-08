package com.fondstore.cart.data.utils

object CartDataUtil {
    private const val CART_URL = "api/cart"

    const val GET_CART_URL = CART_URL
    const val GET_CART_TAG = "GET CART"

    const val UPDATE_CART_ITEM_TAG = "UPDATE CART ITEM"
    const val REMOVE_CART_ITEM_TAG = "REMOVE CART ITEM"

    fun getUpdateCartItemUrl(cartId: String) = "$CART_URL/$cartId/cart_items/"
    fun getRemoveCartItemUrl(itemId: Int, cartId: String) = "$CART_URL/$cartId/cart_items/$itemId/"
}