package com.fondstore.launcher.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fondstore.core.presentation.screenBackground
import com.fondstore.core.presentation.TradeMark

@Composable
fun LauncherScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize().screenBackground(),
        contentAlignment = Alignment.Center
    ) {
        TradeMark()
    }
}