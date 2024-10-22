package com.fondstore.splash

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class SplashScreenModel : StateScreenModel<SplashScreenState>(SplashScreenState()) {

    fun onEvent(event: SplashScreenEvent) {
        when (event) {
            is SplashScreenEvent.Navigate -> navigate(destination = event.destination)
        }
    }

    private fun navigate(destination: SplashScreenDestination?) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }
}