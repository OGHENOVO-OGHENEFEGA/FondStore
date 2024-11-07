package com.fondstore.auth.presentation.signInScreen

import cafe.adriel.voyager.core.model.StateScreenModel
import com.fondstore.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.update

class SignInScreenModel(private val repository: AuthRepository) :
    StateScreenModel<SignInScreenState>(SignInScreenState()) {
    private lateinit var signInJob: Job

    fun onEvent(event: SignInScreenEvent) {
        when (event) {
            is SignInScreenEvent.SetEmail -> setEmail(event.email)
            is SignInScreenEvent.SetPassword -> setPassword(event.password)
            SignInScreenEvent.SignIn -> signIn()
            SignInScreenEvent.CancelSignIn -> cancelSignInJob()
            is SignInScreenEvent.Navigate -> navigate(event.destination)
            SignInScreenEvent.ClearDestination -> clearDestination()
            SignInScreenEvent.ClearScreen -> clearScreen()
            SignInScreenEvent.CloseScreen -> closeScreen()
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

    private fun signIn() {

    }

    private fun cancelSignInJob() {
        if (::signInJob.isInitialized) {
            signInJob.cancel()
        }
    }

    private fun navigate(destination: SignInScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }

    private fun clearDestination() {
        mutableState.update {
            it.copy(destination = null)
        }
    }

    private fun clearScreen() {
        cancelSignInJob()

        mutableState.update {
            SignInScreenState()
        }
    }

    private fun closeScreen() {
        mutableState.update {
            it.copy(isActive = false)
        }
    }
}