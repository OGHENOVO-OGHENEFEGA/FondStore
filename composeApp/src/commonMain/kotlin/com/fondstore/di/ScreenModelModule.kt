package com.fondstore.di

import com.fondstore.app.AppScreenModel
import com.fondstore.auth.presentation.forgotPasswordScreen.ForgotPasswordScreenModel
import com.fondstore.auth.presentation.signInScreen.SignInScreenModel
import com.fondstore.auth.presentation.signUpScreen.SignUpScreenModel
import com.fondstore.cart.presentation.CartScreenModel
import com.fondstore.category.categoriesScreen.CategoriesScreenModel
import com.fondstore.category.categoryScreen.CategoryScreenModel
import com.fondstore.category.trendingCategoryScreen.TrendingCategoryScreenModel
import com.fondstore.contactUs.presentation.ContactUsScreenModel
import com.fondstore.faqs.presentation.FaqsScreenModel
import com.fondstore.favourites.presentation.FavouritesScreenModel
import com.fondstore.helpCentre.HelpCentreScreenModel
import com.fondstore.home.HomeScreenModel
import com.fondstore.launcher.presentation.LauncherScreenModel
import com.fondstore.notification.presentation.NotificationsScreenModel
import com.fondstore.onboarding.OnboardingScreenModel
import com.fondstore.privacyPolicy.presentation.PrivacyPolicyScreenModel
import com.fondstore.product.presentation.productGroupScreen.ProductGroupsScreenModel
import com.fondstore.profile.presentation.ProfileScreenModel
import com.fondstore.returnAndExchangePolicy.presentation.ReturnAndExchangePolicyScreenModel
import com.fondstore.search.SearchScreenModel
import com.fondstore.splash.SplashScreenModel
import com.fondstore.store.StoreScreenModel
import com.fondstore.termsAndConditions.presentation.TermsAndConditionScreenModel
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

    factoryOf(::HomeScreenModel)
    factoryOf(::FavouritesScreenModel)
    factoryOf(::CartScreenModel)

    factoryOf(::ProductGroupsScreenModel)
    factory { CategoriesScreenModel(categories = it.get()) }
    factoryOf(::CategoryScreenModel)
    factoryOf(::TrendingCategoryScreenModel)

    factoryOf(::ProfileScreenModel)

    factoryOf(::HelpCentreScreenModel)
    factoryOf(::FaqsScreenModel)
    factoryOf(::PrivacyPolicyScreenModel)
    factoryOf(::TermsAndConditionScreenModel)
    factoryOf(::ReturnAndExchangePolicyScreenModel)
    factoryOf(::ContactUsScreenModel)

    factoryOf(::SignInScreenModel)
    factoryOf(::SignUpScreenModel)
    factoryOf(::ForgotPasswordScreenModel)
}