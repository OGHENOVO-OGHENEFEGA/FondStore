package com.fondstore.product.data.repositories

import com.fondstore.error.domain.models.Result
import com.fondstore.ktor.data.utils.safeGet
import com.fondstore.product.data.mappers.toError
import com.fondstore.product.data.mappers.toProduct
import com.fondstore.product.data.remote.responses.ProductResponse
import com.fondstore.product.data.remote.responses.ProductSearchResponse
import com.fondstore.product.data.utils.ProductDataUtil
import com.fondstore.product.domain.errors.ProductError
import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.models.Product
import com.fondstore.product.domain.models.Section
import com.fondstore.product.domain.models.SectionItems
import com.fondstore.product.domain.models.Subcategory
import com.fondstore.product.domain.models.TrendingCategoryItems
import com.fondstore.product.domain.repositories.ProductRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess

class KtorProductRepository(private val client: HttpClient) : ProductRepository {
    override suspend fun searchProducts(query: String): Result<List<Product>, ProductError> {
        return client.safeGet(
            urlString = ProductDataUtil.PRODUCT_SEARCH_URL,
            tag = ProductDataUtil.PRODUCT_SEARCH_TAG,
            requestBlock = {
                parameter(key = ProductDataUtil.PRODUCT_SEARCH_PARAM_KEY, value = query)
            },
            responseBlock =  { response ->
                if (response.status.isSuccess()) {
                    val results = response.body<ProductSearchResponse>().results.map(ProductResponse.Success::toProduct)
                    Result.Success(results)
                } else {
                    Result.Error(response.body<ProductResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getExploreProducts(): Result<List<Product>, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): Result<List<Category>, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getBestDeals(): Result<List<Product>, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingCategories(): Result<List<Category>, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularProducts(): Result<List<Product>, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewArrivals(): Result<List<Product>, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubcategories(categoryId: Int): Result<List<Subcategory>, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getSections(
        categoryId: Int,
        subcategoryId: Int
    ): Result<List<Section>, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getSectionItemsInfo(
        categoryId: Int,
        subcategoryId: Int,
        sectionId: Int
    ): Result<SectionItems, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getNextSectionItemsInfo(url: String): Result<SectionItems, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getTrendingCategoryItemsInfo(categoryId: Int): Result<TrendingCategoryItems, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getNextTrendingCategoryItemsInfo(url: String): Result<TrendingCategoryItems, ProductError> {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedProduct(
        productId: String,
        token: String?
    ): Result<Product, ProductError> {
        TODO("Not yet implemented")
    }
}