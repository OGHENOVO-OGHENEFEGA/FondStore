package com.fondstore.faqs.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.faqs.domain.repositories.FaqsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FaqsScreenModel(private val repository: FaqsRepository) :
    StateScreenModel<FaqsScreenState>(FaqsScreenState()) {
    init {
        getFaqs()
    }

    fun onEvent(event: FaqsScreenEvent) {
        when (event) {
            FaqsScreenEvent.RefreshScreen -> refreshScreen()
            FaqsScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun refreshScreen() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isRefreshingScreen = true)
                }
            }

            val result = repository.getFaqs()

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isRefreshingScreen = false, result = result)
                }
            }
        }
    }

    private fun getFaqs() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingFaqs = true)
                }
            }

            val result = repository.getFaqs()

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isGettingFaqs = false, result = result)
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