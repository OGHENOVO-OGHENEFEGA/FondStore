package com.fondstore.helpCentre

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class HelpCenterScreenModel : StateScreenModel<HelpCentreScreenState>(HelpCentreScreenState()) {
    fun onEvent(event: HelpCentreScreenEvent) {
        when(event) {
            is HelpCentreScreenEvent.Navigate -> navigate(event.destination)
            HelpCentreScreenEvent.ClearDestination -> clearDestination()
        }
    }

    private fun navigate(destination: HelpCentreScreenDestination) {
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