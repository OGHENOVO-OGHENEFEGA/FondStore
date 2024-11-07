package com.fondstore.app

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.auth.domain.models.AuthTokensState
import com.fondstore.auth.domain.repositories.AuthRepository
import com.fondstore.product.domain.models.Product
import com.fondstore.product.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppScreenModel(
    private val authRepository: AuthRepository,
    private val productRepository: ProductRepository,
) : StateScreenModel<AppState>(AppState()) {
    private lateinit var getProductJob: Job

    init {
        screenModelScope.launch(Dispatchers.IO) {
            authRepository.getLocalAuthTokensFlow().collectLatest { tokens ->
                withContext(Dispatchers.Main + NonCancellable) {
                    mutableState.update {
                        it.copy(authTokens = tokens)
                    }
                }
            }
        }
    }

    fun onEvent(event: AppEvent) {
        when (event) {
            AppEvent.GetProducts -> getProducts()
            is AppEvent.ToggleProductFavouriteState -> {
                toggleProductFavouriteState(event.product)
            }

            AppEvent.ClearDestination -> clearDestination()
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
                it.copy(productsState = it.productsState.copy(isGettingExploreProducts = true))
            }
        }

        val result = productRepository.getExploreProducts()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(
                    productsState = it.productsState.copy(
                        isGettingExploreProducts = false,
                        exploreProductsResult = result
                    )
                )
            }
        }
    }

    private suspend fun getCategories() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(productsState = it.productsState.copy(isGettingCategories = true))
            }
        }

        val result = productRepository.getCategories()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(
                    productsState = it.productsState.copy(
                        isGettingCategories = false,
                        categoriesResult = result
                    )
                )
            }
        }
    }

    private suspend fun getBestDeals() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(productsState = it.productsState.copy(isGettingBestDeals = true))
            }
        }

        val result = productRepository.getBestDeals()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(
                    productsState = it.productsState.copy(
                        isGettingBestDeals = false,
                        bestDealsResult = result
                    )
                )
            }
        }
    }

    private suspend fun getTrendingCategories() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(productsState = it.productsState.copy(isGettingTrendingCategories = true))
            }
        }

        val result = productRepository.getTrendingCategories()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(
                    productsState = it.productsState.copy(
                        isGettingTrendingCategories = false,
                        trendingCategoriesResult = result
                    )
                )
            }
        }
    }

    private suspend fun getPopularProducts() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(productsState = it.productsState.copy(isGettingPopularProducts = true))
            }
        }

        val result = productRepository.getPopularProducts()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(
                    productsState = it.productsState.copy(
                        isGettingPopularProducts = false,
                        popularProductsResult = result
                    )
                )
            }
        }
    }

    private suspend fun getNewArrivals() {
        withContext(Dispatchers.Main) {
            mutableState.update {
                it.copy(productsState = it.productsState.copy(isGettingNewArrivals = true))
            }
        }

        val result = productRepository.getNewArrivals()

        withContext(Dispatchers.Main + NonCancellable) {
            mutableState.update {
                it.copy(
                    productsState = it.productsState.copy(
                        isGettingNewArrivals = false,
                        newArrivalsResult = result
                    )
                )
            }
        }
    }

    private fun toggleProductFavouriteState(product: Product) {
        if (state.value.authTokens?.state != AuthTokensState.AVAILABLE) {
            mutableState.update {
                it.copy(destination = AppDestination.AuthScreen)
            }
        }
    }

    private fun clearDestination() {
        mutableState.update {
            it.copy(destination = null)
        }
    }
}