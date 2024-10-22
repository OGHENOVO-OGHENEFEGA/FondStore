package com.fondstore.app

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.fondstore.image.presentation.WithCoilConfig
import com.fondstore.ui.presentation.FondStoreTheme
import org.koin.compose.KoinContext

@Composable
fun App() {
    FondStoreTheme {
        KoinContext {
            WithCoilConfig {
                Navigator(AppScreen())
            }
        }
    }
}