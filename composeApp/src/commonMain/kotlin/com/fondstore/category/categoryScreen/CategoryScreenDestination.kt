package com.fondstore.category.categoryScreen

import com.fondstore.product.domain.models.Product

sealed interface CategoryScreenDestination {
    data class ProductScreen(val product: Product) : CategoryScreenDestination
}