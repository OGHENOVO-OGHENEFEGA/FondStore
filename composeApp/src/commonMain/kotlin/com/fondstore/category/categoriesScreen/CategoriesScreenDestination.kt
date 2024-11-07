package com.fondstore.category.categoriesScreen

import com.fondstore.product.domain.models.Category

sealed interface CategoriesScreenDestination {
    data class CategoryScreen(val category: Category) : CategoriesScreenDestination
}