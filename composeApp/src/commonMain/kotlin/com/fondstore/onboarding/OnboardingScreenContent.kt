package com.fondstore.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.fondstore.core.presentation.button
import com.fondstore.core.presentation.screenHorizontalPadding
import com.fondstore.image.DrawablePaths
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Bold
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.next
import fondstore.composeapp.generated.resources.shop_now
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingScreenContent(
    state: OnboardingScreenState,
    onEvent: (OnboardingScreenEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState {
            state.pages.size
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = false
        ) { index ->
            Column(modifier = Modifier.fillMaxWidth()) {
                val page = state.pages[index]

                Card(
                    modifier = Modifier.fillMaxWidth().height(481.dp),
                    shape = RoundedCornerShape(bottomStart = 75.dp, bottomEnd = 75.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Image(
                        painter = painterResource(page.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(36.dp))

                Column(
                    modifier = Modifier.fillMaxWidth().screenHorizontalPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(page.heading),
                        color = MaterialTheme.appColors.color100,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Bold),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(page.text),
                        color = MaterialTheme.appColors.color70,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(21.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                space = 4.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.pages.forEachIndexed { index, _ ->
                val isSelected = index == pagerState.currentPage
                val containerColor = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.primary.copy(0.5f)
                }

                Card(
                    modifier = Modifier.size(width = 15.dp, height = 4.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = CardDefaults.cardColors(containerColor = containerColor)
                ) {

                }
            }
        }

        Spacer(modifier = Modifier.height(21.dp))

        val isLastItem = pagerState.currentPage == state.pages.lastIndex
        val coroutineScope = rememberCoroutineScope()

        Button(
            onClick = {
                if (isLastItem) {
                    onEvent(OnboardingScreenEvent.Navigate(OnboardingScreenDestination.StoreScreen))
                } else {
                    coroutineScope.launch {
                        pagerState.scrollToPage(pagerState.currentPage.plus(1))
                    }
                }
            },
            modifier = Modifier.button().screenHorizontalPadding()
        ) {
            val stringResource = if (isLastItem) Res.string.shop_now else Res.string.next
            val iconPath = if (isLastItem) DrawablePaths.SHOP_NOW else DrawablePaths.NEXT
            val iconModifier = if (isLastItem) {
                Modifier.size(18.dp)
            } else {
                Modifier.size(width = 15.dp, height = 18.dp)
            }

            Text(
                text = stringResource(stringResource),
                fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            AsyncImage(
                model = Res.getUri(iconPath),
                contentDescription = stringResource(stringResource),
                modifier = iconModifier,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
            )
        }
    }
}