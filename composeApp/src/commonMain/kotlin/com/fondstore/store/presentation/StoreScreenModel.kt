package com.fondstore.store.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class StoreScreenModel : StateScreenModel<StoreScreenState>(StoreScreenState()) {

    fun onEvent(event: StoreScreenEvent) {
        when (event) {
            is StoreScreenEvent.Navigate -> navigate(event.destination)
            StoreScreenEvent.ClearDestination -> clearDestination()
        }
    }

    private fun navigate(destination: StoreScreenDestination) {
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