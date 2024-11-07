package com.fondstore.home

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class HomeScreenModel : StateScreenModel<HomeScreenState>(HomeScreenState()) {

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.ToggleProductFavouriteState -> {}
            is HomeScreenEvent.Navigate -> navigate(event.destination)
            HomeScreenEvent.ClearDestination -> clearDestination()
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