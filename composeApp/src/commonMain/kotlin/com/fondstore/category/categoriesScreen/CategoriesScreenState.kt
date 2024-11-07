package com.fondstore.category.categoriesScreen

import com.fondstore.product.domain.models.Category

data class CategoriesScreenState(
    val categories: List<Category>,
    val destination: CategoriesScreenDestination? = null,
    val isActive: Boolean = true
)