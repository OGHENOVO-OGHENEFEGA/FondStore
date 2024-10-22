package com.fondstore.core.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun LoadingButton(
    isLoading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    inverseColors: Boolean = false,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.loadingButtonColors(inverseColors = inverseColors)
    ) {
        if (isLoading) {
            val animationColor =
                if (inverseColors) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onPrimary
                }

            CircleLoadingAnimation(color = animationColor)
        } else {
            content()
        }
    }
}

@Composable
private fun ButtonDefaults.loadingButtonColors(inverseColors: Boolean = false): ButtonColors {
    return if (inverseColors) {
        buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        )
    } else {
        buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
        )
    }
}