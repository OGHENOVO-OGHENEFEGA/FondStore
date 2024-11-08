package com.fondstore.app

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.fondstore.core.presentation.numbClickable
import com.fondstore.image.WithCoilConfig
import com.fondstore.ui.FondStoreTheme
import org.koin.compose.KoinContext

@Composable
fun App() {
    FondStoreTheme {
        KoinContext {
            WithCoilConfig {
                Navigator(AppScreen()) {
                    val focusManager = LocalFocusManager.current

                    Box(modifier = Modifier.numbClickable(onClick = focusManager::clearFocus)) {
                        CurrentScreen()
                    }
                }
            }
        }
    }
}