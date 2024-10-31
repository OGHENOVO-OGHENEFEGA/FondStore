package com.fondstore.di

import com.fondstore.app.AppScreenModel
import com.fondstore.faqs.presentation.FaqsScreenModel
import com.fondstore.helpCentre.HelpCenterScreenModel
import com.fondstore.launcher.presentation.LauncherScreenModel
import com.fondstore.notification.presentation.NotificationsScreenModel
import com.fondstore.onboarding.OnboardingScreenModel
import com.fondstore.privacyPolicy.presentation.PrivacyPolicyScreenModel
import com.fondstore.search.presentation.SearchScreenModel
import com.fondstore.splash.SplashScreenModel
import com.fondstore.store.presentation.StoreScreenModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val screenModelModule = module {
    factoryOf(::AppScreenModel)
    factoryOf(::LauncherScreenModel)
    factoryOf(::SplashScreenModel)
    factoryOf(::OnboardingScreenModel)
    factoryOf(::StoreScreenModel)
    factoryOf(::SearchScreenModel)
    factoryOf(::NotificationsScreenModel)
    factoryOf(::HelpCenterScreenModel)
    factoryOf(::FaqsScreenModel)
    factoryOf(::PrivacyPolicyScreenModel)
}