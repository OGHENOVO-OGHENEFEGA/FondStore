package com.fondstore.category.trendingCategoryScreen

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.error.Result
import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrendingCategoryScreenModel(
    val category: Category,
    private val repository: ProductRepository,
) : StateScreenModel<TrendingCategoryScreenState>(TrendingCategoryScreenState(category = category)) {

    init {
        getItems()
    }

    fun onEvent(event: TrendingCategoryScreenEvent) {
        when (event) {
            TrendingCategoryScreenEvent.GetNextItems -> getNextItems()
            is TrendingCategoryScreenEvent.ToggleProductFavouriteState -> {}
            is TrendingCategoryScreenEvent.Navigate -> navigate(event.destination)
            TrendingCategoryScreenEvent.ClearDestination -> clearDestination()
        }
    }

    private fun getItems() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingItems = true)
                }
            }

            val result = repository.getTrendingCategoryItems(categoryId = state.value.category.id)

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isGettingItems = false, result = result)
                }
            }
        }
    }

    private fun getNextItems() {
        val url = state.value.result?.dataOrNull?.next

        if (!state.value.isGettingNextItems && url != null) {
            screenModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    mutableState.update {
                        it.copy(isGettingItems = true)
                    }
                }

                val result = repository.getNextTrendingCategoryItems(url = url)

                val currentResult = state.value.result

                val newResult = if (result is Result.Success) {
                    when (currentResult) {
                        is Result.Success -> {
                            currentResult.copy(
                                currentResult.data.copy(
                                    count = result.data.count,
                                    next = result.data.next,
                                    previous = result.data.previous,
                                    results = currentResult.data.results.plus(result.data.results)
                                )
                            )
                        }

                        else -> result
                    }
                } else currentResult

                withContext(Dispatchers.Main + NonCancellable) {
                    mutableState.update {
                        it.copy(isGettingItems = false, result = newResult)
                    }
                }
            }
        }
    }

    private fun navigate(destination: TrendingCategoryScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }

    private fun clearDestination() {
        mutableState.update {
            it.copy(destination = null)
        }
    }
}