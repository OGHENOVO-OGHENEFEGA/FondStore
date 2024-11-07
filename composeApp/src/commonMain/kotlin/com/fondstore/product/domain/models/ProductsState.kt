package com.fondstore.product.domain.models

import com.fondstore.error.Result
import com.fondstore.product.domain.errors.CategoryError
import com.fondstore.product.domain.errors.ProductError

data class ProductsState(
    val isGettingExploreProducts: Boolean = false,
    val exploreProductsResult: Result<List<Product>, ProductError>? = null,
    val isGettingCategories: Boolean = false,
    val categoriesResult: Result<List<Category>, CategoryError>? = null,
    val isGettingBestDeals: Boolean = false,
    val bestDealsResult: Result<List<Product>, ProductError>? = null,
    val isGettingTrendingCategories: Boolean = false,
    val trendingCategoriesResult: Result<List<Category>, CategoryError>? = null,
    val isGettingPopularProducts: Boolean = false,
    val popularProductsResult: Result<List<Product>, ProductError>? = null,
    val isGettingNewArrivals: Boolean = false,
    val newArrivalsResult: Result<List<Product>, ProductError>? = null
)
