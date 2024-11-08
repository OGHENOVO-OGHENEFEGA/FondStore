package com.fondstore.profile.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fondstore.core.presentation.numbClickable
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.profile_image
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileImage(size: Dp = 40.dp, enabled: Boolean = true, onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Filled.AccountCircle,
        contentDescription = stringResource(Res.string.profile_image),
        tint = MaterialTheme.appColors.color30,
        modifier = Modifier.size(size).numbClickable(onClick = onClick, enabled = enabled)
    )
}