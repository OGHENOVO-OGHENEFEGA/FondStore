package com.fondstore.favourites.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class FavouritesScreenModel : StateScreenModel<FavouritesScreenState>(FavouritesScreenState()) {

    fun onEvent(event: FavouritesScreenEvent) {
        when (event) {
            is FavouritesScreenEvent.RemoveProduct -> {}
            is FavouritesScreenEvent.Navigate -> navigate(event.destination)
            FavouritesScreenEvent.ClearDestination -> clearDestination()
        }
    }

    private fun navigate(destination: FavouritesScreenDestination) {
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