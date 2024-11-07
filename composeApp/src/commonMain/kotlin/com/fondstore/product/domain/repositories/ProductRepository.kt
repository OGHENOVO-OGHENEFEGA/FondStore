package com.fondstore.product.domain.repositories

import com.fondstore.error.Result
import com.fondstore.product.domain.errors.CategoryError
import com.fondstore.product.domain.errors.ProductError
import com.fondstore.product.domain.errors.SectionError
import com.fondstore.product.domain.errors.SectionItemsError
import com.fondstore.product.domain.errors.SubcategoryError
import com.fondstore.product.domain.errors.TrendingCategoryItemsError
import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.models.Product
import com.fondstore.product.domain.models.Section
import com.fondstore.product.domain.models.SectionItems
import com.fondstore.product.domain.models.Subcategory
import com.fondstore.product.domain.models.TrendingCategoryItems

interface ProductRepository {
    suspend fun searchProducts(query: String): Result<List<Product>, ProductError>
    suspend fun getExploreProducts(): Result<List<Product>, ProductError>
    suspend fun getCategories(): Result<List<Category>, CategoryError>
    suspend fun getBestDeals(): Result<List<Product>, ProductError>
    suspend fun getTrendingCategories(): Result<List<Category>, CategoryError>
    suspend fun getPopularProducts(): Result<List<Product>, ProductError>
    suspend fun getNewArrivals(): Result<List<Product>, ProductError>
    suspend fun getProduct(productId: String, token: String?): Result<Product, ProductError>
    suspend fun getSubcategories(categoryId: Int): Result<List<Subcategory>, SubcategoryError>
    suspend fun getSections(
        categoryId: Int,
        subcategoryId: Int,
    ): Result<List<Section>, SectionError>

    suspend fun getSectionItems(
        categoryId: Int,
        subcategoryId: Int,
        sectionId: Int,
    ): Result<SectionItems, SectionItemsError>

    suspend fun getNextSectionItems(url: String): Result<SectionItems, SectionItemsError>
    suspend fun getTrendingCategoryItems(categoryId: Int): Result<TrendingCategoryItems, TrendingCategoryItemsError>
    suspend fun getNextTrendingCategoryItems(url: String): Result<TrendingCategoryItems, TrendingCategoryItemsError>
}