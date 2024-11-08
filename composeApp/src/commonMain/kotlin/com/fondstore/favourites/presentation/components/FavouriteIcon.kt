package com.fondstore.favourites.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.core.presentation.numbClickable
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.add_to_favourites
import fondstore.composeapp.generated.resources.remove_from_favourites
import org.jetbrains.compose.resources.stringResource

@Composable
fun FavouriteIcon(isFavourite: Boolean, isLoading: Boolean = false, onClick: () -> Unit) {
    val modifier = Modifier.size(35.dp)

    if (isLoading) {
        CircleLoadingAnimation(modifier = modifier, strokeWidth = 5.dp)
    } else {
        val icon = if (isFavourite) {
            Icons.Filled.Favorite
        } else {
            Icons.Filled.FavoriteBorder
        }

        val contentDescription = if (isFavourite) {
            stringResource(Res.string.remove_from_favourites)
        } else {
            stringResource(Res.string.add_to_favourites)
        }

        val tint = if (isFavourite) MaterialTheme.colorScheme.primary else MaterialTheme.appColors.color100

        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier.numbClickable(enabled = !isLoading, onClick = onClick)
        )
    }
}