package com.fondstore.contactUs.presentation

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.contactUs.domain.errors.HelpCentreError
import com.fondstore.contactUs.domain.repositories.ContactUsRepository
import com.fondstore.error.domain.models.Result
import com.fondstore.helpCentre.utils.HelpCentreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactUsScreenModel(
    private val repository: ContactUsRepository,
    private val helpCentreManager: HelpCentreManager,
) : StateScreenModel<ContactUsScreenState>(ContactUsScreenState()) {

    init {
        getContactOptions()
    }

    fun onEvent(event: ContactUsScreenEvent) {
        when (event) {
            ContactUsScreenEvent.RefreshScreen -> refreshScreen()
            ContactUsScreenEvent.OpenEmail -> openEmail()
            ContactUsScreenEvent.OpenPhone -> openPhone()
            ContactUsScreenEvent.OpenAddress -> openAddress()
            is ContactUsScreenEvent.OpenUrl -> openUrl(event.url)
            ContactUsScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun refreshScreen() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isRefreshingScreen = true)
                }
            }

            val socialsResult = repository.getSocials()

            val helpCentreResult = when (socialsResult) {
                is Result.Error -> {
                    Result.Error(HelpCentreError(error = socialsResult.error.error))
                }

                is Result.Exception -> {
                    Result.Exception(socialsResult.exception)
                }

                is Result.Success -> {
                    repository.getHelpCentre()
                }
            }

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(
                        isRefreshingScreen = false,
                        socialsResult = socialsResult,
                        helpCentreResult = helpCentreResult
                    )
                }
            }
        }
    }

    private fun getContactOptions() {
        screenModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingContactOptions = true)
                }
            }

            val socialsResult = repository.getSocials()

            val helpCentreResult = when (socialsResult) {
                is Result.Error -> {
                    Result.Error(HelpCentreError(error = socialsResult.error.error))
                }

                is Result.Exception -> {
                    Result.Exception(socialsResult.exception)
                }

                is Result.Success -> {
                    repository.getHelpCentre()
                }
            }

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(
                        isGettingContactOptions = false,
                        socialsResult = socialsResult,
                        helpCentreResult = helpCentreResult
                    )
                }
            }
        }
    }

    private fun openEmail() {
        val address = state.value.helpCentreResult?.dataOrNull?.email

        if (address != null) {
            helpCentreManager.openEmail(address = address)
        }
    }

    private fun openPhone() {
        val phoneNumber = state.value.helpCentreResult?.dataOrNull?.phoneNumber

        if (phoneNumber != null) {
            helpCentreManager.openPhone(number = phoneNumber)
        }
    }

    private fun openAddress() {
        val address = state.value.helpCentreResult?.dataOrNull?.address

        if (address != null) {
            helpCentreManager.openAddress(address = address)
        }
    }

    private fun openUrl(url: String) {
        helpCentreManager.openUrl(url = url)
    }

    private fun closeScreen() {
        mutableState.update {
            it.copy(isActive = false)
        }
    }
}