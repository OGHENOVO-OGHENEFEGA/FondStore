package com.fondstore.favourites.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.ui.appColors

@Composable
fun FavouriteCard(
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(),
    yOffSet: Dp = 0.dp,
    onClick: () -> Unit
) {
    val containerColor = 
        if (isFavourite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
    
    val contentColor =
        if (isFavourite) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            disabledContainerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = contentColor
        ),
        shape = CircleShape,
        border = BorderStroke(1.dp, MaterialTheme.appColors.color10),
        onClick = onClick,
        enabled = !isLoading
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(contentPadding).offset(y = yOffSet),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                val loadingAnimationColor = if (isFavourite) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.primary
                }

                CircleLoadingAnimation(
                    modifier = Modifier.size(12.dp),
                    strokeWidth = 2.dp,
                    color = loadingAnimationColor
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}