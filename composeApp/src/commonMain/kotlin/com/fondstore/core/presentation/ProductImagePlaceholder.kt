package com.fondstore.core.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.image_query_failure_message
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductImagePlaceholder() {
    Icon(
        imageVector = Icons.Filled.LocalMall,
        contentDescription = stringResource(Res.string.image_query_failure_message),
        tint = MaterialTheme.appColors.color50
    )
}