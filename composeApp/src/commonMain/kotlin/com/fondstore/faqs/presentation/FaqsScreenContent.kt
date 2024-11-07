package com.fondstore.faqs.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.BackNavTopAppBar
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.defaultClickable
import com.fondstore.core.presentation.screenBackground
import com.fondstore.faqs.domain.models.Faq
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import dev.materii.pullrefresh.PullRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.faqs
import fondstore.composeapp.generated.resources.hide_question
import fondstore.composeapp.generated.resources.no_faqs
import fondstore.composeapp.generated.resources.show_question
import org.jetbrains.compose.resources.stringResource

@Composable
fun FaqsScreenContent(state: FaqsScreenState, onEvent: (FaqsScreenEvent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopAppBar(
                onNavigateBack = {
                    onEvent(FaqsScreenEvent.CloseScreen)
                },
                actions = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.HelpOutline,
                        contentDescription = stringResource(Res.string.faqs),
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
                    onEvent(FaqsScreenEvent.RefreshScreen)
                }
            )

            PullRefreshLayout(
                state = refreshState,
                modifier = Modifier.fillMaxSize(),
                enabled = !state.isGettingFaqs
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    items(state.result?.dataOrNull ?: listOf()) { faq ->
                        FaqBox(faq = faq)
                    }
                }
            }

            if (state.isGettingFaqs) {
                LoadingAnimationBox()
            } else if (state.result?.dataOrNull.isNullOrEmpty()) {
                Text(
                    text = stringResource(Res.string.no_faqs),
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.appColors.color100,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun FaqBox(faq: Faq) {
    var isAnswerVisible by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = faq.question,
                color = MaterialTheme.appColors.color100,
                fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(0.6f)
            )

            val contentDescriptionResource =
                if (isAnswerVisible) Res.string.hide_question else Res.string.show_question

            val degrees = if (isAnswerVisible) 0f else 45f

            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(contentDescriptionResource),
                tint = MaterialTheme.appColors.color70,
                modifier = Modifier.rotate(degrees).defaultClickable {
                    isAnswerVisible = !isAnswerVisible
                }
            )
        }

        AnimatedVisibility(
            visible = isAnswerVisible,
            modifier = Modifier.fillMaxWidth().padding(end = 40.dp)
        ) {
            Text(
                text = faq.answer,
                color = MaterialTheme.appColors.color70,
                fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}