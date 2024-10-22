package com.fondstore.launcher.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.launcher.domain.repositories.LauncherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LauncherScreenModel(private val repository: LauncherRepository) :
    StateScreenModel<LauncherScreenState>(LauncherScreenState()) {

    init {
        screenModelScope.launch(Dispatchers.IO) {
            delay(LauncherScreenDefaults.SCREEN_DURATION)

            val isUserOnBoard = repository.isUserOnboard()

            withContext(Dispatchers.Main) {
                if (isUserOnBoard) {
                    mutableState.update {
                        it.copy(destination = LauncherScreenDestination.StoreScreen)
                    }
                } else {
                    mutableState.update {
                        it.copy(destination = LauncherScreenDestination.SplashScreen)
                    }
                }
            }
        }
    }
}