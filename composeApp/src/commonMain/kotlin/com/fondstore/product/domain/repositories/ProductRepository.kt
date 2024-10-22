package com.fondstore.product.domain.repositories

import com.fondstore.error.domain.models.Result
import com.fondstore.product.domain.errors.ProductError
import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.models.Product
import com.fondstore.product.domain.models.Section
import com.fondstore.product.domain.models.SectionItems
import com.fondstore.product.domain.models.Subcategory
import com.fondstore.product.domain.models.TrendingCategoryItems

interface ProductRepository {
    suspend fun searchProducts(query: String) : Result<List<Product>, ProductError>
    suspend fun getExploreProducts(): Result<List<Product>, ProductError>
    suspend fun getCategories(): Result<List<Category>, ProductError>
    suspend fun getBestDeals(): Result<List<Product>, ProductError>
    suspend fun getTrendingCategories(): Result<List<Category>, ProductError>
    suspend fun getPopularProducts(): Result<List<Product>, ProductError>
    suspend fun getNewArrivals(): Result<List<Product>, ProductError>
    suspend fun getSubcategories(categoryId: Int): Result<List<Subcategory>, ProductError>
    suspend fun getSections(categoryId: Int, subcategoryId: Int): Result<List<Section>, ProductError>
    suspend fun getSectionItemsInfo(categoryId: Int, subcategoryId: Int, sectionId: Int): Result<SectionItems, ProductError>
    suspend fun getNextSectionItemsInfo(url: String): Result<SectionItems, ProductError>
    suspend fun getTrendingCategoryItemsInfo(categoryId: Int): Result<TrendingCategoryItems, ProductError>
    suspend fun getNextTrendingCategoryItemsInfo(url: String): Result<TrendingCategoryItems, ProductError>
    suspend fun getSelectedProduct(productId: String, token: String?): Result<Product, ProductError>
}