package com.fondstore.product.presentation.productGroupScreen

import com.fondstore.product.domain.models.Product
import com.fondstore.product.domain.models.ProductGroup

sealed interface ProductGroupsScreenEvent {
    data class SelectGroup(val group: ProductGroup) : ProductGroupsScreenEvent
    data class ToggleProductFavouriteState(val product: Product) : ProductGroupsScreenEvent
    data class Navigate(val destination: ProductGroupsScreenDestination) : ProductGroupsScreenEvent
    data object ClearDestination : ProductGroupsScreenEvent
}