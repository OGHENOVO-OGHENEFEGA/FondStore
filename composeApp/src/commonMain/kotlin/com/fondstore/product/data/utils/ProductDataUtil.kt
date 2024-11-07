package com.fondstore.product.data.utils

object ProductDataUtil {
    private const val API_URL = "api"
    const val PRODUCT_SEARCH_URL = "$API_URL/item_search"
    const val PRODUCT_SEARCH_PARAM_KEY = "query"
    const val PRODUCT_SEARCH_TAG = "PRODUCT SEARCH"

    const val EXPLORE_PRODUCTS_URL = "$API_URL/items/random_products"
    const val GET_EXPLORE_PRODUCTS_TAG = "GET EXPLORE PRODUCTS"

    const val CATEGORIES_URL = "$API_URL/categories"
    const val GET_CATEGORIES_TAG = "GET CATEGORIES"

    const val BEST_DEALS_URL = "$API_URL/best_deals"
    const val GET_BEST_DEALS_TAG = "GET BEST DEALS"

    const val TRENDING_CATEGORIES_URL = "$API_URL/trending_categories"
    const val GET_TRENDING_CATEGORIES_TAG = "GET TRENDING CATEGORIES"

    const val POPULAR_PRODUCTS_URL = "$API_URL/popular_items"
    const val GET_POPULAR_PRODUCTS_TAG = "GET POPULAR PRODUCTS"

    const val NEW_ARRIVALS_URL = "$API_URL/new_arrivals"
    const val GET_NEW_ARRIVALS_TAG = "GET NEW ARRIVALS"

    const val GET_PRODUCT_TAG = "GET PRODUCT"

    const val GET_SUBCATEGORIES_TAG = "GET SUBCATEGORIES"

    const val GET_SECTIONS_TAG = "GET SECTIONS"

    const val GET_SECTION_ITEMS_TAG = "GET SECTION ITEMS"
    const val GET_NEXT_SECTION_ITEMS_TAG = "GET NEXT SECTION ITEMS"

    const val GET_TRENDING_CATEGORY_ITEMS_TAG = "GET TRENDING CATEGORY ITEMS TAG"
    const val GET_NEXT_TRENDING_CATEGORY_ITEMS_TAG = "GET NEXT TRENDING CATEGORY ITEMS TAG"

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
    fun getProductUrl(productId: String) = "$API_URL/items/$productId"
}