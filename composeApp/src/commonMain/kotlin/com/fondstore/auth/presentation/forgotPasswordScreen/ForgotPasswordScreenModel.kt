package com.fondstore.auth.presentation.forgotPasswordScreen

import cafe.adriel.voyager.core.model.StateScreenModel
import com.fondstore.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.update

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

    }

    private fun navigate(destination: ForgotPasswordScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }
}