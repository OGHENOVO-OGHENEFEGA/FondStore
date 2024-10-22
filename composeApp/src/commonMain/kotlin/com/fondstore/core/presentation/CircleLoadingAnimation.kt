package com.fondstore.core.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircleLoadingAnimation(
    modifier: Modifier = Modifier.size(20.dp),
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 3.dp
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = strokeWidth,
        strokeCap = StrokeCap.Round
    )
}