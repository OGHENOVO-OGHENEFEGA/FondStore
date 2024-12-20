package com.fondstore.auth.presentation.signUpScreen

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpScreenModel(private val repository: AuthRepository) :
    StateScreenModel<SignUpScreenState>(SignUpScreenState()) {

    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.SetUsername -> setUsername(event.username)
            is SignUpScreenEvent.SetEmail -> setEmail(event.email)
            is SignUpScreenEvent.SetPassword -> setPassword(event.password)
            is SignUpScreenEvent.SetConfirmPassword -> setConfirmPassword(event.password)
            SignUpScreenEvent.SignUp -> signUp()
            is SignUpScreenEvent.Navigate -> navigate(event.destination)
        }
    }

    private fun setUsername(username: String) {
        mutableState.update {
            it.copy(username = username)
        }
    }

    private fun setEmail(email: String) {
        mutableState.update {
            it.copy(email = email)
        }
    }

    private fun setPassword(password: String) {
        mutableState.update {
            it.copy(password = password)
        }
    }

    private fun setConfirmPassword(password: String) {
        mutableState.update {
            it.copy(confirmPassword = password)
        }
    }

    private fun signUp() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isSigningUp = true)
                }
            }

            val result = repository.signUp(
                username = state.value.username,
                email = state.value.email,
                password = state.value.password,
                confirmPassword = state.value.confirmPassword
            )

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isSigningUp = false, result = result)
                }
            }
        }
    }

    private fun navigate(destination: SignUpScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }
}