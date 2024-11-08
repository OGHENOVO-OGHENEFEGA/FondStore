package com.fondstore.cart.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class CartScreenModel : StateScreenModel<CartScreenState>(CartScreenState()) {

    fun onEvent(event: CartScreenEvent) {
        when (event) {
            is CartScreenEvent.RemoveProduct -> {}
            is CartScreenEvent.Navigate -> navigate(event.destination)
            CartScreenEvent.ClearDestination -> clearDestination()
        }
    }

    private fun navigate(destination: CartScreenDestination) {
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