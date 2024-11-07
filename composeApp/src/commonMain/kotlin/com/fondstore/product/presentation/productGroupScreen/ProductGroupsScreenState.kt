package com.fondstore.product.presentation.productGroupScreen

import com.fondstore.product.domain.models.ProductGroup

data class ProductGroupsScreenState(
    val selectedGroup: ProductGroup,
    val destination: ProductGroupsScreenDestination? = null,
)