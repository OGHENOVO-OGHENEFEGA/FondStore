package com.fondstore.product.data.remote.responses

import com.fondstore.rating.data.remote.responses.RatingResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface ProductResponse {
    @Serializable
    data class Success(
        @SerialName("additional_images")
        val additionalImages: List<String> = listOf(),
        @SerialName("average_rating")
        val averageRating: Float? = null,
        @SerialName("category")
        val category: Int = 0,
        @SerialName("colour")
        val colours: List<String> = listOf(),
        @SerialName("description")
        val description: String = "",
        @SerialName("favorite_id")
        val favoriteId: String? = null,
        @SerialName("has_bought")
        val hasBought: Boolean = false,
        @SerialName("id")
        val id: String = "",
        @SerialName("image")
        val image: String = "",
        @SerialName("is_favourite")
        val isFavourite: Boolean = false,
        @SerialName("name")
        val name: String = "",
        @SerialName("new_price")
        val newPrice: String? = null,
        @SerialName("price")
        val price: String = "",
        @SerialName("ratings")
        val ratings: List<RatingResponse> = listOf(),
        @SerialName("section")
        val section: Int = 0,
        @SerialName("size")
        val sizes: List<SizeResponse> = listOf(),
        @SerialName("style")
        val style: String? = null,
        @SerialName("sub_category")
        val subCategory: Int = 0,
        @SerialName("weight")
        val weight: String = ""
    ) : ProductResponse

    @Serializable
    data class Error(
        @SerialName("detail")
        val detail: String = "",
        @SerialName("error")
        val error: String = ""
    ) : ProductResponse
}