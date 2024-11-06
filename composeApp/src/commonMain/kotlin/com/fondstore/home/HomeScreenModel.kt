package com.fondstore.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.product.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenModel(private val repository: ProductRepository) :
    StateScreenModel<HomeScreenState>(HomeScreenState()) {
    private lateinit var getProductJob: Job

    init {
        getProducts()
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.RefreshScreen -> getProducts()

            is HomeScreenEvent.ToggleProductFavouriteState -> {

            }

            is HomeScreenEvent.Navigate -> navigate(event.destination)
            HomeScreenEvent.ClearDestination -> clearDestination()
        }
    }

    private fun getProducts() {
        if (::getProductJob.isInitialized) {
            getProductJob.cancel()
        }

        getProductJob = screenModelScope.launch(Dispatchers.IO) {
            launch { getExploreProducts() }
            launch { getCategories() }
            launch { getBestDeals() }
            launch { getTrendingCategories() }
            launch { getPopularProducts() }
            launch { getNewArrivals() }
        }
    }

    private suspend fun getExploreProducts() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(isGettingExploreProducts = true)
            }
        }

        val result = repository.getExploreProducts()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(isGettingExploreProducts = false, exploreProductsResult = result)
            }
        }
    }

    private suspend fun getCategories() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(isGettingCategories = true)
            }
        }

        val result = repository.getCategories()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(isGettingCategories = false, categoriesResult = result)
            }
        }
    }

    private suspend fun getBestDeals() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(isGettingBestDeals = true)
            }
        }

        val result = repository.getBestDeals()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(isGettingBestDeals = false, bestDealsResult = result)
            }
        }
    }

    private suspend fun getTrendingCategories() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(isGettingTrendingCategories = true)
            }
        }

        val result = repository.getTrendingCategories()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(isGettingTrendingCategories = false, trendingCategoriesResult = result)
            }
        }
    }

    private suspend fun getPopularProducts() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(isGettingPopularProducts = true)
            }
        }

        val result = repository.getPopularProducts()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(isGettingPopularProducts = false, popularProductsResult = result)
            }
        }
    }

    private suspend fun getNewArrivals() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(isGettingNewArrivals = true)
            }
        }

        val result = repository.getNewArrivals()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(isGettingNewArrivals = false, newArrivalsResult = result)
            }
        }
    }

    private fun navigate(destination: HomeScreenDestination) {
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