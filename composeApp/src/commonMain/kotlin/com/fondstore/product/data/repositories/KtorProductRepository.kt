package com.fondstore.product.data.repositories

import com.fondstore.error.Result
import com.fondstore.ktor.safeGet
import com.fondstore.product.data.mappers.toCategory
import com.fondstore.product.data.mappers.toError
import com.fondstore.product.data.mappers.toProduct
import com.fondstore.product.data.mappers.toSection
import com.fondstore.product.data.mappers.toSectionItems
import com.fondstore.product.data.mappers.toSubcategory
import com.fondstore.product.data.remote.responses.CategoryResponse
import com.fondstore.product.data.remote.responses.NewArrivalResponse
import com.fondstore.product.data.remote.responses.ProductResponse
import com.fondstore.product.data.remote.responses.ProductSearchResponse
import com.fondstore.product.data.remote.responses.SectionItemsResponse
import com.fondstore.product.data.remote.responses.SectionResponse
import com.fondstore.product.data.remote.responses.SubcategoryResponse
import com.fondstore.product.data.utils.ProductDataUtil
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
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    val results =
                        response.body<ProductSearchResponse>().results.map(ProductResponse.Success::toProduct)
                    Result.Success(results)
                } else {
                    Result.Error(response.body<ProductResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getExploreProducts(): Result<List<Product>, ProductError> {
        return client.safeGet(
            urlString = ProductDataUtil.EXPLORE_PRODUCTS_URL,
            tag = ProductDataUtil.GET_EXPLORE_PRODUCTS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<ProductResponse.Success>>()
                            .map(ProductResponse.Success::toProduct)
                    )
                } else {
                    Result.Error(response.body<ProductResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getCategories(): Result<List<Category>, CategoryError> {
        return client.safeGet(
            urlString = ProductDataUtil.CATEGORIES_URL,
            tag = ProductDataUtil.GET_CATEGORIES_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<CategoryResponse.Success>>()
                            .map(CategoryResponse.Success::toCategory)
                    )
                } else {
                    Result.Error(response.body<CategoryResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getBestDeals(): Result<List<Product>, ProductError> {
        return client.safeGet(
            urlString = ProductDataUtil.BEST_DEALS_URL,
            tag = ProductDataUtil.GET_BEST_DEALS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<ProductResponse.Success>>()
                            .map(ProductResponse.Success::toProduct)
                    )
                } else {
                    Result.Error(response.body<ProductResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getTrendingCategories(): Result<List<Category>, CategoryError> {
        return client.safeGet(
            urlString = ProductDataUtil.TRENDING_CATEGORIES_URL,
            tag = ProductDataUtil.GET_TRENDING_CATEGORIES_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<CategoryResponse.Success>>()
                            .map(CategoryResponse.Success::toCategory)
                    )
                } else {
                    Result.Error(response.body<CategoryResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getPopularProducts(): Result<List<Product>, ProductError> {
        return client.safeGet(
            urlString = ProductDataUtil.POPULAR_PRODUCTS_URL,
            tag = ProductDataUtil.GET_POPULAR_PRODUCTS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<ProductResponse.Success>>()
                            .map(ProductResponse.Success::toProduct)
                    )
                } else {
                    Result.Error(response.body<ProductResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getNewArrivals(): Result<List<Product>, ProductError> {
        return client.safeGet(
            urlString = ProductDataUtil.NEW_ARRIVALS_URL,
            tag = ProductDataUtil.GET_NEW_ARRIVALS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<NewArrivalResponse.Success>>()
                            .map(NewArrivalResponse.Success::toProduct)
                    )
                } else {
                    Result.Error(response.body<NewArrivalResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getSubcategories(categoryId: Int): Result<List<Subcategory>, SubcategoryError> {
        return client.safeGet(
            urlString = ProductDataUtil.getSubcategoriesUrl(categoryId = categoryId),
            tag = ProductDataUtil.GET_SUBCATEGORIES_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<SubcategoryResponse.Success>>()
                            .map(SubcategoryResponse.Success::toSubcategory)
                    )
                } else {
                    Result.Error(response.body<SubcategoryResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getSections(
        categoryId: Int,
        subcategoryId: Int,
    ): Result<List<Section>, SectionError> {
        return client.safeGet(
            urlString = ProductDataUtil.getSectionsUrl(
                categoryId = categoryId,
                subcategoryId = subcategoryId
            ),
            tag = ProductDataUtil.GET_SECTIONS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(
                        response.body<List<SectionResponse.Success>>()
                            .map(SectionResponse.Success::toSection)
                    )
                } else {
                    Result.Error(response.body<SectionResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getSectionItems(
        categoryId: Int,
        subcategoryId: Int,
        sectionId: Int,
    ): Result<SectionItems, SectionItemsError> {
        return client.safeGet(
            urlString = ProductDataUtil.getSectionsItemsUrl(
                categoryId = categoryId,
                subcategoryId = subcategoryId,
                sectionId = sectionId
            ),
            tag = ProductDataUtil.GET_SECTION_ITEMS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(response.body<SectionItemsResponse.Success>().toSectionItems())
                } else {
                    Result.Error(response.body<SectionItemsResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getNextSectionItems(url: String): Result<SectionItems, SectionItemsError> {
        return client.safeGet(
            urlString = url,
            tag = ProductDataUtil.GET_NEXT_SECTION_ITEMS_TAG,
            responseBlock = { response ->
                if (response.status.isSuccess()) {
                    Result.Success(response.body<SectionItemsResponse.Success>().toSectionItems())
                } else {
                    Result.Error(response.body<SectionItemsResponse.Error>().toError())
                }
            }
        )
    }

    override suspend fun getTrendingCategoryItems(categoryId: Int): Result<TrendingCategoryItems, TrendingCategoryItemsError> {
        TODO("Not yet implemented")
    }

    override suspend fun getNextTrendingCategoryItems(url: String): Result<TrendingCategoryItems, TrendingCategoryItemsError> {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedProduct(
        productId: String,
        token: String?,
    ): Result<Product, ProductError> {
        TODO("Not yet implemented")
    }
}