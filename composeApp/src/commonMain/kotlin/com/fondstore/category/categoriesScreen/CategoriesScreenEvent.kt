package com.fondstore.category.categoriesScreen

sealed interface CategoriesScreenEvent {
    data class Navigate(val destination: CategoriesScreenDestination) : CategoriesScreenEvent
    data object ClearDestination : CategoriesScreenEvent
    data object CloseScreen : CategoriesScreenEvent
}