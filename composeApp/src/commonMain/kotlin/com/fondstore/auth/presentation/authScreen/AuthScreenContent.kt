package com.fondstore.auth.presentation.authScreen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.fondstore.auth.presentation.signInScreen.SignInScreen

@Composable
fun AuthScreenContent() {
    Navigator(SignInScreen()) { navigator ->
        SlideTransition(navigator = navigator)
    }
}