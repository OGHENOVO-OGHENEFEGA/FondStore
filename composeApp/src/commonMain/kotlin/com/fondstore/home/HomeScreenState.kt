package com.fondstore.home

import com.fondstore.error.domain.models.Result
import com.fondstore.product.domain.errors.CategoryError
import com.fondstore.product.domain.errors.ProductError
import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.models.Product

data class HomeScreenState(
    val isRefreshingScreen: Boolean = false,
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
    val newArrivalsResult: Result<List<Product>, ProductError>? = null,
    val destination: HomeScreenDestination? = null,
)
