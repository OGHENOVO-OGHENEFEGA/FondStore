package com.fondstore.ui.presentation

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val colorScheme: ColorScheme
    @Composable get() {
        return lightColorScheme(
            primary = Primary,
            secondaryContainer = SecondaryContainer,
            surfaceVariant = SurfaceVariant
        )
    }

@Composable
fun FondStoreTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colorScheme, content = content)
}