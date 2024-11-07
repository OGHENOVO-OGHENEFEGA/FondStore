package com.fondstore.returnAndExchangePolicy.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.BackNavTopAppBar
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.screenBackground
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.no_policy
import fondstore.composeapp.generated.resources.privacy_policy
import org.jetbrains.compose.resources.stringResource

@Composable
fun ReturnAndExchangePolicyScreenContent(
    state: ReturnAndExchangePolicyScreenState,
    onEvent: (ReturnAndExchangePolicyScreenEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopAppBar(
                onNavigateBack = {
                    onEvent(ReturnAndExchangePolicyScreenEvent.CloseScreen)
                },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.Policy,
                        contentDescription = stringResource(Res.string.privacy_policy),
                        tint = MaterialTheme.appColors.color100
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().screenBackground().padding(innerPadding)) {
            val refreshState = rememberPullRefreshState(
                refreshing = state.isRefreshingScreen,
                onRefresh = {
                    onEvent(ReturnAndExchangePolicyScreenEvent.RefreshScreen)
                }
            )

            PullRefreshLayout(
                state = refreshState,
                modifier = Modifier.fillMaxSize(),
                enabled = !state.isGettingPolicy
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    item {
                        Text(text = state.result?.dataOrNull?.policy ?: "")
                    }
                }
            }

            if (state.isGettingPolicy) {
                LoadingAnimationBox()
            } else if (state.result?.dataOrNull?.policy.isNullOrBlank()) {
                Text(
                    text = stringResource(Res.string.no_policy),
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.appColors.color100,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 16.sp
                )
            }
        }
    }
}