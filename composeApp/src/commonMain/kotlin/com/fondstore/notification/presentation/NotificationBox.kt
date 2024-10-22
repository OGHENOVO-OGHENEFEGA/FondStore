package com.fondstore.notification.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fondstore.composeapp.generated.resources.DMSans_SemiBold
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.notification
import com.fondstore.notification.domain.models.Notification
import com.fondstore.resources.presentation.fontFamilyResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotificationBox(notification: Notification, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.notificationCardColors()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedIconButton(
                onClick = {

                },
                modifier = Modifier.size(32.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                colors = IconButtonDefaults.notificationIconButtonColors(),
                enabled = false
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = stringResource(Res.string.notification),
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = notification.notice,
                fontSize = 16.sp,
                fontFamily = fontFamilyResource(Res.font.DMSans_SemiBold)
            )
        }
    }
}

@Composable
private fun CardDefaults.notificationCardColors(): CardColors {
    return cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
}

@Composable
private fun IconButtonDefaults.notificationIconButtonColors(): IconButtonColors {
    return outlinedIconButtonColors(
        containerColor = MaterialTheme.colorScheme.onPrimary,
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
        disabledContentColor = MaterialTheme.colorScheme.primary
    )
}