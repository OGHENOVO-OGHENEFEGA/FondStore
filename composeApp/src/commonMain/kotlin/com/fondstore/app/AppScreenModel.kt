package com.fondstore.app

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.auth.domain.models.AuthTokensState
import com.fondstore.auth.domain.repositories.AuthRepository
import com.fondstore.error.Result
import com.fondstore.favourites.domain.repositories.FavouritesRepository
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
    private val favouritesRepository: FavouritesRepository,
) : StateScreenModel<AppState>(AppState()) {
    private lateinit var getProductJob: Job

    init {
        screenModelScope.launch(Dispatchers.IO) {
            authRepository.getLocalAuthTokensFlow().collectLatest { tokens ->
                withContext(Dispatchers.Main + NonCancellable) {
                    mutableState.update {
                        it.copy(authTokens = tokens)
                    }

                    if (tokens?.state == AuthTokensState.AVAILABLE) {
                        loadAuthenticationRequiredData()
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

            is AppEvent.RemoveProductFromFavourites -> {
                removeProductFromFavourites(event.product)
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
        } else if (
            !state.value.favouritesState.requestLoadingList.map(Product::id).contains(product.id)
        ) {
            screenModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    mutableState.update {
                        val loadingList = it.favouritesState.requestLoadingList.toMutableList()
                        loadingList.add(product)

                        it.copy(favouritesState = it.favouritesState.copy(requestLoadingList = loadingList))
                    }
                }

                val favourites = state.value.favouritesState.result?.dataOrNull ?: listOf()

                val isProductFavourite = favourites.map(Product::id).contains(product.id)

                val favouriteResult = if (isProductFavourite) {
                    val favouriteId = favourites.find { it.id == product.id }?.favoriteId

                    favouriteId?.let { id ->
                        favouritesRepository.removeFromFavourites(
                            favouriteId = id,
                            token = state.value.authTokens?.access
                        )
                    }
                } else {
                    favouritesRepository.addToFavourites(
                        productId = product.id,
                        token = state.value.authTokens?.access
                    )
                }

                val result = if (favouriteResult is Result.Success) {
                    favouritesRepository.getFavourites(token = state.value.authTokens?.access)
                } else null

                val newResult = when {
                    result is Result.Success -> result

                    favouriteResult is Result.Success -> {
                        val newFavourites = favourites.toMutableList()

                        if (isProductFavourite) {
                            newFavourites.remove(product)
                        } else {
                            newFavourites.add(product)
                        }

                        Result.Success(newFavourites.toList())
                    }

                    else -> state.value.favouritesState.result
                }

                withContext(Dispatchers.Main + NonCancellable) {
                    mutableState.update {
                        val loadingList = it.favouritesState.requestLoadingList.toMutableList()
                        loadingList.remove(product)

                        it.copy(
                            favouritesState = it.favouritesState.copy(
                                requestLoadingList = loadingList,
                                result = newResult
                            )
                        )
                    }
                }
            }
        }
    }

    private fun removeProductFromFavourites(product: Product) {
        if (state.value.authTokens?.state != AuthTokensState.AVAILABLE) {
            mutableState.update {
                it.copy(destination = AppDestination.AuthScreen)
            }
        } else if (
            !state.value.favouritesState.requestLoadingList.map(Product::id).contains(product.id)
        ) {
            screenModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    mutableState.update {
                        val loadingList = it.favouritesState.requestLoadingList.toMutableList()
                        loadingList.add(product)

                        it.copy(favouritesState = it.favouritesState.copy(requestLoadingList = loadingList))
                    }
                }

                val favourites = state.value.favouritesState.result?.dataOrNull ?: listOf()

                val favouriteId = favourites.find { it.id == product.id }?.favoriteId

                val favouriteResult = favouriteId?.let { id ->
                    favouritesRepository.removeFromFavourites(
                        favouriteId = id,
                        token = state.value.authTokens?.access
                    )
                }

                val result = if (favouriteResult is Result.Success) {
                    favouritesRepository.getFavourites(token = state.value.authTokens?.access)
                } else null

                val newResult = when {
                    result is Result.Success -> result

                    favouriteResult is Result.Success -> {
                        val newFavourites = favourites.toMutableList()
                        newFavourites.remove(product)

                        Result.Success(newFavourites.toList())
                    }

                    else -> state.value.favouritesState.result
                }

                withContext(Dispatchers.Main + NonCancellable) {
                    mutableState.update {
                        val loadingList = it.favouritesState.requestLoadingList.toMutableList()
                        loadingList.remove(product)

                        it.copy(
                            favouritesState = it.favouritesState.copy(
                                requestLoadingList = loadingList,
                                result = newResult
                            )
                        )
                    }
                }
            }
        }
    }

    private fun loadAuthenticationRequiredData() {
        getFavourites()
    }

    private fun getFavourites() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(favouritesState = it.favouritesState.copy(isGettingFavourites = true))
                }
            }

            val result = favouritesRepository.getFavourites(token = state.value.authTokens?.access)

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(
                        favouritesState = it.favouritesState.copy(
                            isGettingFavourites = false,
                            result = result
                        )
                    )
                }
            }
        }
    }

    private fun clearDestination() {
        mutableState.update {
            it.copy(destination = null)
        }
    }
}