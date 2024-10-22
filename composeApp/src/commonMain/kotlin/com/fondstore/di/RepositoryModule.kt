package com.fondstore.di

import com.fondstore.launcher.data.repositories.RealmLauncherRepository
import com.fondstore.launcher.domain.repositories.LauncherRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<LauncherRepository> {
        RealmLauncherRepository(get())
    }
}