package com.fondstore.category.categoriesScreen

import cafe.adriel.voyager.core.model.StateScreenModel
import com.fondstore.product.domain.models.Category
import kotlinx.coroutines.flow.update

class CategoriesScreenModel(categories: List<Category>) :
    StateScreenModel<CategoriesScreenState>(CategoriesScreenState(categories = categories)) {

    fun onEvent(event: CategoriesScreenEvent) {
        when (event) {
            is CategoriesScreenEvent.Navigate -> navigate(event.destination)
            CategoriesScreenEvent.ClearDestination -> clearDestination()
            CategoriesScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun navigate(destination: CategoriesScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }

    private fun clearDestination() {
        mutableState.update {
            it.copy(destination = null)
        }
    }

    private fun closeScreen() {
        mutableState.update {
            it.copy(isActive = false)
        }
    }
}