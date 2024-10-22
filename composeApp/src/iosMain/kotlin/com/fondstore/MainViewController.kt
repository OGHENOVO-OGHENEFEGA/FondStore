package com.fondstore

import androidx.compose.ui.window.ComposeUIViewController
import com.fondstore.app.App
import com.fondstore.di.KoinManager

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinManager().start()
    },
    content = {
        App()
    }
)