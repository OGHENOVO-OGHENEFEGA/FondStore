package com.fondstore.di

import com.fondstore.auth.data.repositories.KtorAuthRepository
import com.fondstore.auth.domain.repositories.AuthRepository
import com.fondstore.launcher.data.repositories.RealmLauncherRepository
import com.fondstore.launcher.domain.repositories.LauncherRepository
import com.fondstore.notification.data.repositories.KtorNotificationRepository
import com.fondstore.notification.domain.repositories.NotificationRepository
import com.fondstore.product.data.repositories.KtorProductRepository
import com.fondstore.product.domain.repositories.ProductRepository
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
}