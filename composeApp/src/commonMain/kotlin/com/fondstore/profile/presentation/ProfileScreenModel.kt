package com.fondstore.profile.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.auth.domain.repositories.AuthRepository
import com.fondstore.profile.domain.repositories.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileScreenModel(
    private val authRepository: AuthRepository,
    private val profileRepository: ProfileRepository
) : StateScreenModel<ProfileScreenState>(ProfileScreenState()) {

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            ProfileScreenEvent.ToggleEditMode -> toggleEditMode()

            is ProfileScreenEvent.SetFirstName -> setFirstName(event.name)
            is ProfileScreenEvent.SetLastName -> setLastName(event.name)
            is ProfileScreenEvent.SetPhoneNumber -> setPhoneNumber(event.number)

            ProfileScreenEvent.ShowSignOutDialog -> {}

            ProfileScreenEvent.UploadProfile -> uploadProfile()

            is ProfileScreenEvent.Navigate -> navigate(event.destination)
            ProfileScreenEvent.ClearDestination -> clearDestination()
        }
    }

    private fun toggleEditMode() {
//        mutableState.update {
//            it.copy(isInEditMode = !it.isInEditMode)
//        }
//
//        val profile = profileManager.state.value.profile
//
//        mutableState.update {
//            it.copy(
//                firstname = if (it.isInEditMode) profile.firstname else "",
//                lastname = if (it.isInEditMode) profile.lastname else "",
//                phoneNumber = if (it.isInEditMode) profile.phoneNumber else ""
//            )
//        }
    }

    private fun setFirstName(name: String) {
        mutableState.update {
            it.copy(firstname = name)
        }
    }

    private fun setLastName(name: String) {
        mutableState.update {
            it.copy(lastname = name)
        }
    }

    private fun setPhoneNumber(number: String) {
        mutableState.update {
            it.copy(phoneNumber = number)
        }
    }

    private fun uploadProfile() {
        screenModelScope.launch(Dispatchers.IO) {
            profileRepository.updateProfile(
                firstname = state.value.firstname,
                lastname = state.value.lastname,
                phoneNumber = state.value.phoneNumber,
                token = authRepository.getLocalAuthTokens()?.access,
            )

            withContext(Dispatchers.Main + NonCancellable) {
                toggleEditMode()
            }
        }
    }

    private fun navigate(destination: ProfileScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }

    private fun clearDestination() {
        mutableState.update {
            it.copy(destination = null)
        }
    }
}