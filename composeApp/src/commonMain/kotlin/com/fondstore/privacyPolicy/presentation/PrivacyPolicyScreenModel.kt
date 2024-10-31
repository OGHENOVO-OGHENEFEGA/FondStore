package com.fondstore.privacyPolicy.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.fondstore.privacyPolicy.domain.repositories.PrivacyPolicyRepository
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update

class PrivacyPolicyScreenModel(private val repository: PrivacyPolicyRepository) :
    StateScreenModel<PrivacyPolicyScreenState>(PrivacyPolicyScreenState()) {

    init {
        getPrivacyPolicy()
    }

    fun onEvent(event: PrivacyPolicyScreenEvent) {
        when (event) {
            PrivacyPolicyScreenEvent.RefreshScreen -> refreshScreen()
            PrivacyPolicyScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun refreshScreen() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isRefreshingScreen = true)
                }
            }

            val result = repository.getPrivacyPolicy()

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isRefreshingScreen = false, result = result)
                }
            }
        }
    }

    private fun getPrivacyPolicy() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingPolicy = true)
                }
            }

            val result = repository.getPrivacyPolicy()

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isGettingPolicy = false, result = result)
                }
            }
        }
    }

    private fun closeScreen() {
        mutableState.update {
            it.copy(isActive = false)
        }
    }
}