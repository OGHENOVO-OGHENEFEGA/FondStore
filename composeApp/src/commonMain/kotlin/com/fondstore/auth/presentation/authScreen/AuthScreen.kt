package com.fondstore.auth.presentation.authScreen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class AuthScreen : Screen {

    @Composable
    override fun Content() {
        AuthScreenContent()
    }
}