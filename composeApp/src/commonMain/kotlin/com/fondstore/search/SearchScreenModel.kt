package com.fondstore.search

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.product.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchScreenModel(private val productRepository: ProductRepository) :
    StateScreenModel<SearchScreenState>(SearchScreenState()) {

    fun onEvent(event: SearchScreenEvent) {
        when (event) {
            is SearchScreenEvent.SetSearchQuery -> setSearchQuery(event.query)
            SearchScreenEvent.SearchProducts -> searchProducts()
            is SearchScreenEvent.ToggleProductFavouritesState -> {}
            is SearchScreenEvent.Navigate -> navigate(event.destination)
            SearchScreenEvent.ClearDestination -> clearDestination()
            SearchScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun setSearchQuery(query: String) {
        mutableState.update {
            it.copy(query = query)
        }
    }

    private fun searchProducts() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isSearching = true)
                }
            }

            val result = productRepository.searchProducts(query = state.value.query)

            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isSearching = false, result = result)
                }
            }
        }
    }

    private fun navigate(destination: SearchScreenDestination) {
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