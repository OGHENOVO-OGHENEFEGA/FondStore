package com.fondstore.notification.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.core.presentation.screenBackground
import com.fondstore.error.domain.models.Result
import com.fondstore.image.presentation.DrawablePaths
import com.fondstore.resources.presentation.fontFamilyResource
import com.fondstore.ui.presentation.appColors
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.no_notifications
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
        Box(
            modifier = Modifier.fillMaxSize().screenBackground().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
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
                CircleLoadingAnimation()
            } else if (state.result !is Result.Success) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(Res.string.no_notifications),
                        color = MaterialTheme.appColors.color70,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}