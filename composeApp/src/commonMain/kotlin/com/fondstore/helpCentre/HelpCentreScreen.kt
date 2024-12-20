package com.fondstore.helpCentre

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.fondstore.contactUs.presentation.ContactUsScreen
import com.fondstore.faqs.presentation.FaqsScreen
import com.fondstore.privacyPolicy.presentation.PrivacyPolicyScreen
import com.fondstore.returnAndExchangePolicy.presentation.ReturnAndExchangePolicyScreen
import com.fondstore.store.StoreScreen
import com.fondstore.termsAndConditions.presentation.TermsAndConditionScreen
import com.fondstore.voyager.NavigationKey
import com.fondstore.voyager.koinNavigatorScreenModel
import com.fondstore.voyager.push

class HelpCentreScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current?.parent?.parent

        val screenModel = koinNavigatorScreenModel<HelpCentreScreenModel>(navigator = navigator)
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
                navigator = navigator,
                navigationKey = NavigationKey.Klass(StoreScreen::class),
                onNavigation = {
                    screenModel.onEvent(HelpCentreScreenEvent.ClearDestination)
                }
            )
        }

        HelpCentreScreenContent(onEvent = screenModel::onEvent)
    }
}