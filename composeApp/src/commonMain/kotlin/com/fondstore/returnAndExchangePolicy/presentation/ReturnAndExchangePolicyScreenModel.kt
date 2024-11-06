package com.fondstore.returnAndExchangePolicy.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.fondstore.returnAndExchangePolicy.domain.repositories.ReturnAndExchangePolicyRepository
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update

class ReturnAndExchangePolicyScreenModel(private val repository: ReturnAndExchangePolicyRepository) :
    StateScreenModel<ReturnAndExchangePolicyScreenState>(ReturnAndExchangePolicyScreenState()) {

    init {
        getReturnAndExchangePolicy()
    }

    fun onEvent(event: ReturnAndExchangePolicyScreenEvent) {
        when (event) {
            ReturnAndExchangePolicyScreenEvent.RefreshScreen -> refreshScreen()
            ReturnAndExchangePolicyScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun refreshScreen() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isRefreshingScreen = true)
                }
            }

            val result = repository.getReturnAndExchangePolicy()

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isRefreshingScreen = false, result = result)
                }
            }
        }
    }

    private fun getReturnAndExchangePolicy() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingPolicy = true)
                }
            }

            val result = repository.getReturnAndExchangePolicy()

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