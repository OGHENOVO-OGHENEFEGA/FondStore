package com.fondstore.product.data.utils

object ProductDataUtil {
    private const val API_URL = "api"
    const val PRODUCT_SEARCH_URL = "$API_URL/item_search"
    const val PRODUCT_SEARCH_PARAM_KEY = "query"
    const val PRODUCT_SEARCH_TAG = "PRODUCT SEARCH"

    const val RANDOM_PRODUCTS_URL = "$API_URL/items/random_products"
    const val CATEGORIES_URL = "$API_URL/categories"
    const val NEW_ARRIVALS_URL = "$API_URL/new_arrivals"
    const val POPULAR_PRODUCTS_URL = "$API_URL/popular_items"
    const val BEST_DEALS_URL = "$API_URL/best_deals"
    const val TRENDING_CATEGORIES_URL = "$API_URL/trending_categories"

    fun getSubcategoriesUrl(categoryId: Int) = "$CATEGORIES_URL/$categoryId/subcategories"

    fun getSectionsUrl(categoryId: Int, subcategoryId: Int): String {
        return "${getSubcategoriesUrl(categoryId = categoryId)}/$subcategoryId/sections"
    }

    fun getSectionsItemsUrl(categoryId: Int, subcategoryId: Int, sectionId: Int): String {
        return "${
            getSectionsUrl(
                categoryId = categoryId,
                subcategoryId = subcategoryId
            )
        }/$sectionId/items"
    }

    fun getTrendingCategoryItemsUrl(categoryId: Int) = "$TRENDING_CATEGORIES_URL/$categoryId/items"
    fun getProductInfoUrl(productId: String) = "$API_URL/items/$productId"
}