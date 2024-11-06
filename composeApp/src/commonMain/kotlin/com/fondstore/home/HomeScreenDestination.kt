package com.fondstore.home

import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.models.Product
import com.fondstore.product.domain.models.ProductGroup

sealed interface HomeScreenDestination {
    data object AuthScreen : HomeScreenDestination
    data class ProductGroupsScreen(val group: ProductGroup) : HomeScreenDestination
    data class ProductScreen(val product: Product) : HomeScreenDestination
    data class CategoryScreen(val category: Category) : HomeScreenDestination
    data class CategoriesScreen(val categories: List<Category>) : HomeScreenDestination
    data class TrendingCategoryScreen(val category: Category) : HomeScreenDestination
}