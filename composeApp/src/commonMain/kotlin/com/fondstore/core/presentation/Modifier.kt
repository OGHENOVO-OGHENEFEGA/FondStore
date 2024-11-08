package com.fondstore.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.screenBackground() = background(MaterialTheme.colorScheme.onPrimary)

@Composable
fun Modifier.screenTopPadding() = padding(top = 10.dp)

@Composable
fun Modifier.screenBottomPadding() = padding(bottom = 10.dp)

@Composable
fun Modifier.screenVerticalPadding() = screenTopPadding().screenBottomPadding()

@Composable
fun Modifier.screenHorizontalPadding() = padding(horizontal = 10.dp)

@Composable
fun Modifier.screenPadding() = screenHorizontalPadding().screenVerticalPadding()

@Composable
fun Modifier.buttonHeight() = height(60.dp)

@Composable
fun Modifier.button() = fillMaxWidth().buttonHeight()

@Composable
fun Modifier.numbClickable(enabled: Boolean = true, onClick: () -> Unit) = composed(
    factory = {
        this.then(
            Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = enabled,
                onClick = { onClick() }
            )
        )
    }
)