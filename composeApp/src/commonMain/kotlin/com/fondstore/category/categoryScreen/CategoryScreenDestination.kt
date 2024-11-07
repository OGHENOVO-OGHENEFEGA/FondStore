package com.fondstore.category.categoryScreen

import com.fondstore.product.domain.models.Product

sealed interface CategoryScreenDestination {
    data object AuthScreen : CategoryScreenDestination
    data class ProductScreen(val product: Product) : CategoryScreenDestination
}