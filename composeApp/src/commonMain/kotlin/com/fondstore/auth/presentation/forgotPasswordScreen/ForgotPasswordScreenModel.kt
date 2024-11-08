package com.fondstore.auth.presentation.forgotPasswordScreen

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForgotPasswordScreenModel(private val repository: AuthRepository) :
    StateScreenModel<ForgotPasswordScreenState>(ForgotPasswordScreenState()) {
    fun onEvent(event: ForgotPasswordScreenEvent) {
        when (event) {
            is ForgotPasswordScreenEvent.SetEmail -> setEmail(event.email)
            ForgotPasswordScreenEvent.ResetPassword -> resetPassword()
            is ForgotPasswordScreenEvent.Navigate -> navigate(event.destination)
        }
    }

    private fun setEmail(email: String) {
        mutableState.update {
            it.copy(email = email)
        }
    }

    private fun resetPassword() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isResettingPassword = true)
                }
            }

            val result = repository.resetPassword(email = state.value.email)

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isResettingPassword = false, result = result)
                }
            }
        }
    }

    private fun navigate(destination: ForgotPasswordScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }
}