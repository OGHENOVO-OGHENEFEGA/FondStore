package com.fondstore.helpCentre

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import com.fondstore.contactUs.presentation.ContactUsScreen
import com.fondstore.faqs.presentation.FaqsScreen
import com.fondstore.privacyPolicy.presentation.PrivacyPolicyScreen
import com.fondstore.returnAndExchangePolicy.presentation.ReturnAndExchangePolicyScreen
import com.fondstore.store.presentation.StoreScreen
import com.fondstore.termsAndConditions.presentation.TermsAndConditionScreen
import com.fondstore.voyager.presentation.NavigationKey
import com.fondstore.voyager.presentation.push

class HelpCentreScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HelpCentreScreenModel>()
        val state by screenModel.state.collectAsState()

        val destination = state.destination

        if (destination != null) {
            val screen = when (destination) {
                HelpCentreScreenDestination.FaqsScreen -> FaqsScreen()
                HelpCentreScreenDestination.PrivacyPolicyScreen -> PrivacyPolicyScreen()
                HelpCentreScreenDestination.TermsAndConditionScreen -> TermsAndConditionScreen()
                HelpCentreScreenDestination.ReturnAndExchangePolicyScreen -> ReturnAndExchangePolicyScreen()
                HelpCentreScreenDestination.ContactUsScreen -> ContactUsScreen()
            }

            push(
                screen = screen,
                navigator = LocalNavigator.current?.parent?.parent,
                navigationKey = NavigationKey.Klass(StoreScreen::class),
                onNavigation = {
                    screenModel.onEvent(HelpCentreScreenEvent.ClearDestination)
                }
            )
        }

        HelpCentreScreenContent(onEvent = screenModel::onEvent)
    }
}