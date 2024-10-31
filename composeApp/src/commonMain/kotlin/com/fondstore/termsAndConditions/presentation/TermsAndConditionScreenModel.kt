package com.fondstore.termsAndConditions.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.fondstore.termsAndConditions.domain.repositories.TermsAndConditionRepository
import kotlinx.coroutines.flow.update

class TermsAndConditionScreenModel(private val repository: TermsAndConditionRepository) :
    StateScreenModel<TermsAndConditionScreenState>(TermsAndConditionScreenState()) {

    init {
        getTermsAndCondition()
    }

    fun onEvent(event: TermsAndConditionScreenEvent) {
        when (event) {
            TermsAndConditionScreenEvent.RefreshScreen -> refreshScreen()
            TermsAndConditionScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun refreshScreen() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isRefreshingScreen = true)
                }
            }

            val result = repository.getTermsAndCondition()

            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isRefreshingScreen = false, result = result)
                }
            }
        }
    }

    private fun getTermsAndCondition() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingTermsAndCondition = true)
                }
            }

            val result = repository.getTermsAndCondition()

            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingTermsAndCondition = false, result = result)
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