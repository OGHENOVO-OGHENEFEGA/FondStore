package com.fondstore.notification.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.fondstore.core.presentation.BackNavTopAppBar
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.screenBackground
import com.fondstore.error.Result
import com.fondstore.image.DrawablePaths
import com.fondstore.notification.domain.models.Notification
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_SemiBold
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.no_notifications
import fondstore.composeapp.generated.resources.notification
import fondstore.composeapp.generated.resources.notifications
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NotificationsScreenContent(
    state: NotificationsScreenState,
    onEvent: (NotificationsScreenEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopAppBar(
                onNavigateBack = {
                    onEvent(NotificationsScreenEvent.CloseScreen)
                },
                actions = {
                    AsyncImage(
                        model = Res.getUri(DrawablePaths.NOTIFICATIONS),
                        contentDescription = stringResource(Res.string.notifications),
                        modifier = Modifier.size(width = 18.dp, height = 24.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.appColors.color100)
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().screenBackground().padding(innerPadding)) {
            Column(modifier = Modifier.fillMaxSize()) {
                val refreshState = rememberPullRefreshState(
                    refreshing = state.isRefreshingScreen,
                    onRefresh = {
                        onEvent(NotificationsScreenEvent.RefreshScreen)
                    }
                )
                
                PullRefreshLayout(
                    state = refreshState,
                    modifier = Modifier.fillMaxSize(),
                    enabled = !state.isGettingNotifications
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(10.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(state.result?.dataOrNull ?: listOf()) { notification ->
                            NotificationBox(
                                notification = notification,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }

            if (state.isGettingNotifications && !state.isRefreshingScreen) {
                LoadingAnimationBox()
            } else if (state.result !is Result.Success) {
                Text(
                    text = stringResource(Res.string.no_notifications),
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.appColors.color70,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun NotificationBox(notification: Notification, modifier: Modifier = Modifier) {
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