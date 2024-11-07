package com.fondstore.contactUs.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.PinDrop
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.fondstore.core.presentation.BackNavTopAppBar
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.screenBackground
import com.fondstore.error.Result
import com.fondstore.ui.appColors
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.address
import fondstore.composeapp.generated.resources.copy
import fondstore.composeapp.generated.resources.email
import fondstore.composeapp.generated.resources.open
import fondstore.composeapp.generated.resources.phone
import fondstore.composeapp.generated.resources.privacy_policy
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@Composable
fun ContactUsScreenContent(state: ContactUsScreenState, onEvent: (ContactUsScreenEvent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopAppBar(
                onNavigateBack = {
                    onEvent(ContactUsScreenEvent.CloseScreen)
                },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.SupportAgent,
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
                    onEvent(ContactUsScreenEvent.RefreshScreen)
                }
            )
            
            PullRefreshLayout(
                state = refreshState,
                modifier = Modifier.fillMaxSize(),
                enabled = !state.isGettingContactOptions
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    if (state.socialsResult is Result.Success && state.helpCentreResult is Result.Success && state.helpCentreResult.data != null) {
                        ContactOption(
                            icon = Icons.Filled.Mail,
                            title = stringResource(Res.string.email),
                            text = state.helpCentreResult.data.email,
                            actionIcon = Icons.AutoMirrored.Outlined.Send
                        ) {
                            onEvent(ContactUsScreenEvent.OpenEmail)
                        }

                        ContactOption(
                            icon = Icons.Filled.Phone,
                            title = stringResource(Res.string.phone),
                            text = state.helpCentreResult.data.phoneNumber,
                            actionIcon = Icons.Outlined.Call
                        ) {
                            onEvent(ContactUsScreenEvent.OpenPhone)
                        }

                        ContactOption(
                            icon = Icons.Filled.Map,
                            title = stringResource(Res.string.address),
                            text = state.helpCentreResult.data.address,
                            actionIcon = Icons.Outlined.PinDrop,
                        ) {
                            onEvent(ContactUsScreenEvent.OpenAddress)
                        }

                        state.socialsResult.data.forEach { social ->
                            ContactOption(
                                icon = FontAwesomeIcons.AllIcons.find {
                                    it.name.lowercase() == social.name.lowercase()
                                },
                                title = social.name,
                                text = social.url,
                                actionIcon = Icons.AutoMirrored.Outlined.OpenInNew
                            ) {
                                onEvent(ContactUsScreenEvent.OpenUrl(social.url))
                            }
                        }

                    }
                }
            }

            if (state.isGettingContactOptions) {
                LoadingAnimationBox()
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ContactOption(
    icon: ImageVector?,
    title: String,
    text: String,
    actionIcon: ImageVector,
    onAction: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        icon?.let { imageVector ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = title,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(text = title)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = text, modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.width(20.dp))

            val clipboardManager = LocalClipboardManager.current

            FilledIconButton(
                onClick = {
                    clipboardManager.setText(
                        buildAnnotatedString {
                            append(text)
                        }
                    )
                },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ContentCopy,
                    contentDescription = stringResource(Res.string.copy),
                    modifier = Modifier.size(14.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            FilledIconButton(
                onClick = onAction,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = stringResource(Res.string.open),
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}