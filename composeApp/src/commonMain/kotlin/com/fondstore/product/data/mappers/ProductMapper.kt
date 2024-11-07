package com.fondstore.product.data.mappers

import com.fondstore.product.data.remote.responses.CategoryResponse
import com.fondstore.product.data.remote.responses.NewArrivalResponse
import com.fondstore.product.data.remote.responses.ProductResponse
import com.fondstore.product.data.remote.responses.SectionItemsResponse
import com.fondstore.product.data.remote.responses.SectionResponse
import com.fondstore.product.data.remote.responses.SizeResponse
import com.fondstore.product.data.remote.responses.SubcategoryResponse
import com.fondstore.product.data.remote.responses.TrendingCategoryItemsResponse
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
import com.fondstore.product.domain.models.Size
import com.fondstore.product.domain.models.Subcategory
import com.fondstore.product.domain.models.TrendingCategoryItems
import com.fondstore.rating.data.mappers.toRating
import com.fondstore.rating.data.remote.responses.RatingResponse

fun ProductResponse.Success.toProduct(): Product {
    return Product(
        additionalImages = additionalImages,
        averageRating = averageRating,
        category = category,
        colours = colours,
        description = description,
        favoriteId = favoriteId,
        hasBought = hasBought,
        id = id,
        image = image,
        isFavourite = isFavourite,
        name = name,
        newPrice = newPrice,
        price = price,
        ratings = ratings.map(RatingResponse::toRating),
        section = section,
        sizes = sizes.map(SizeResponse::toSize),
        style = style,
        subCategory = subCategory,
        weight = weight
    )
}

fun ProductResponse.Error.toError(): ProductError {
    return ProductError(error = error.ifBlank { detail })
}

fun SizeResponse.toSize(): Size {
    return Size(id = id, name = name, quantity = quantity)
}

fun CategoryResponse.Success.toCategory(): Category {
    return Category(description = description, id = id, image = image, name = name)
}

fun CategoryResponse.Error.toError(): CategoryError {
    return CategoryError(error = error.ifBlank { detail })
}

fun NewArrivalResponse.Success.toProduct(): Product {
    return item.toProduct()
}

fun NewArrivalResponse.Error.toError(): ProductError {
    return ProductError(error = error.ifBlank { detail })
}

fun SubcategoryResponse.Success.toSubcategory(): Subcategory {
    return Subcategory(description = description, id = id, image = image, name = name)
}

fun SubcategoryResponse.Error.toError(): SubcategoryError {
    return SubcategoryError(error = error.ifBlank { detail })
}

fun SectionResponse.Success.toSection(): Section {
    return Section(category = category, id = id, name = name, subCategory = subCategory)
}

fun SectionResponse.Error.toError(): SectionError {
    return SectionError(error = error.ifBlank { detail })
}

fun SectionItemsResponse.Success.toSectionItems(): SectionItems {
    return SectionItems(
        count = count,
        next = next,
        previous = previous,
        results = results.map(ProductResponse.Success::toProduct)
    )
}

fun SectionItemsResponse.Error.toError(): SectionItemsError {
    return SectionItemsError(error = error.ifBlank { detail })
}

fun TrendingCategoryItemsResponse.Success.toCategory(): TrendingCategoryItems {
    return TrendingCategoryItems(
        count = count,
        next = next,
        previous = previous,
        results = results.map(ProductResponse.Success::toProduct)
    )
}

fun TrendingCategoryItemsResponse.Error.toError(): TrendingCategoryItemsError {
    return TrendingCategoryItemsError(error = error.ifBlank { detail })
}