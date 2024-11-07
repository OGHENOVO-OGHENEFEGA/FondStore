package com.fondstore.category.categoryScreen

import com.fondstore.product.domain.models.Product
import com.fondstore.product.domain.models.Section
import com.fondstore.product.domain.models.Subcategory

sealed interface CategoryScreenEvent {
    data object GetSubcategories : CategoryScreenEvent
    data class SelectSubcategory(val subcategory: Subcategory) : CategoryScreenEvent
    data class SelectSection(val subcategory: Subcategory, val section: Section) : CategoryScreenEvent
    data class GetNextSectionItems(val section: Section, val url: String) : CategoryScreenEvent
    data class ToggleProductFavouritesState(val product: Product) : CategoryScreenEvent
    data class Navigate(val destination: CategoryScreenDestination) : CategoryScreenEvent
    data object ClearDestination : CategoryScreenEvent
    data object CloseScreen : CategoryScreenEvent
}