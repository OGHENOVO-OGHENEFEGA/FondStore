package com.fondstore.di

import com.fondstore.auth.data.repositories.KtorAuthRepository
import com.fondstore.auth.domain.repositories.AuthRepository
import com.fondstore.cart.data.repositories.KtorCartRepository
import com.fondstore.cart.domain.repositories.CartRepository
import com.fondstore.contactUs.data.repositories.KtorContactUsRepository
import com.fondstore.contactUs.domain.repositories.ContactUsRepository
import com.fondstore.faqs.data.repositories.KtorFaqsRepository
import com.fondstore.faqs.domain.repositories.FaqsRepository
import com.fondstore.favourites.data.repositories.KtorFavouritesRepository
import com.fondstore.favourites.domain.repositories.FavouritesRepository
import com.fondstore.launcher.data.repositories.RealmLauncherRepository
import com.fondstore.launcher.domain.repositories.LauncherRepository
import com.fondstore.notification.data.repositories.KtorNotificationRepository
import com.fondstore.notification.domain.repositories.NotificationRepository
import com.fondstore.privacyPolicy.data.repositories.KtorPrivacyPolicyRepository
import com.fondstore.privacyPolicy.domain.repositories.PrivacyPolicyRepository
import com.fondstore.product.data.repositories.KtorProductRepository
import com.fondstore.product.domain.repositories.ProductRepository
import com.fondstore.profile.data.repositories.KtorProfileRepository
import com.fondstore.profile.domain.repositories.ProfileRepository
import com.fondstore.returnAndExchangePolicy.data.repositories.KtorReturnAndExchangePolicyRepository
import com.fondstore.returnAndExchangePolicy.domain.repositories.ReturnAndExchangePolicyRepository
import com.fondstore.termsAndConditions.data.repositories.KtorTermsAndConditionRepository
import com.fondstore.termsAndConditions.domain.repositories.TermsAndConditionRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<LauncherRepository> {
        RealmLauncherRepository(get())
    }

    factory<AuthRepository> {
        KtorAuthRepository(database = get(), client = get())
    }

    factory<NotificationRepository> {
        KtorNotificationRepository(get())
    }

    factory<ProductRepository> {
        KtorProductRepository(get())
    }

    factory<FavouritesRepository> {
        KtorFavouritesRepository(get())
    }

    factory<CartRepository> {
        KtorCartRepository(get())
    }

    factory<ProfileRepository> {
        KtorProfileRepository(database = get(), client = get())
    }

    factory<FaqsRepository> {
        KtorFaqsRepository(get())
    }

    factory<PrivacyPolicyRepository> {
        KtorPrivacyPolicyRepository(get())
    }

    factory<TermsAndConditionRepository> {
        KtorTermsAndConditionRepository(get())
    }

    factory<ReturnAndExchangePolicyRepository> {
        KtorReturnAndExchangePolicyRepository(get())
    }

    factory<ContactUsRepository> {
        KtorContactUsRepository(get())
    }
}