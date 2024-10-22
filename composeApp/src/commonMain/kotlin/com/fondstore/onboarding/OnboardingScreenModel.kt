package com.fondstore.onboarding

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.launcher.domain.repositories.LauncherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingScreenModel(
    pages: List<OnboardingScreenPage>,
    private val repository: LauncherRepository
) : StateScreenModel<OnboardingScreenState>(OnboardingScreenState(pages)) {

    fun onEvent(event: OnboardingScreenEvent) {
        when (event) {
            is OnboardingScreenEvent.Navigate -> navigate(event.destination)
        }
    }

    private fun navigate(destination: OnboardingScreenDestination) {
        screenModelScope.launch(Dispatchers.IO) {
            if (destination is OnboardingScreenDestination.StoreScreen) {
                repository.setUserOnboard()
            }

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(destination = destination)
                }
            }
        }
    }
}