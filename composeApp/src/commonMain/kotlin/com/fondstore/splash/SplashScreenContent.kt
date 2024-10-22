package com.fondstore.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.button
import com.fondstore.core.presentation.screenBackground
import com.fondstore.resources.presentation.fontFamilyResource
import com.fondstore.ui.presentation.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.DMSans_SemiBold
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.get_ready_to_unlock_your_fashion_potential
import fondstore.composeapp.generated.resources.get_started
import fondstore.composeapp.generated.resources.splash_screen_image_1
import fondstore.composeapp.generated.resources.splash_screen_image_2
import fondstore.composeapp.generated.resources.splash_screen_image_3
import fondstore.composeapp.generated.resources.splash_screen_image_4
import fondstore.composeapp.generated.resources.splash_screen_image_5
import fondstore.composeapp.generated.resources.splash_screen_image_6
import fondstore.composeapp.generated.resources.welcome_to_fondstore
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SplashScreenContent(onEvent: (SplashScreenEvent) -> Unit) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize().screenBackground()) {
        Column(modifier = Modifier.fillMaxSize()) {
            val scrollState = rememberScrollState()

            LaunchedEffect(scrollState.maxValue) {
                if (scrollState.canScrollForward) {
                    scrollState.scrollTo(scrollState.maxValue.div(2))
                }
            }

            val itemWidth = this@BoxWithConstraints.maxWidth.div(2)

            Column(
                modifier = Modifier.horizontalScroll(state = scrollState, enabled = false),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ImageCardRow(
                    resources = listOf(
                        Res.drawable.splash_screen_image_1,
                        Res.drawable.splash_screen_image_2,
                        Res.drawable.splash_screen_image_3
                    ),
                    modifier = Modifier.height(250.dp),
                    itemModifier = Modifier.width(itemWidth).fillMaxHeight()
                )

                ImageCardRow(
                    resources = listOf(
                        Res.drawable.splash_screen_image_4,
                        Res.drawable.splash_screen_image_5,
                        Res.drawable.splash_screen_image_6
                    ),
                    modifier = Modifier.height(250.dp),
                    itemModifier = Modifier.width(itemWidth).fillMaxHeight()
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.welcome_to_fondstore),
                    color = MaterialTheme.appColors.color100,
                    fontFamily = fontFamilyResource(Res.font.DMSans_SemiBold),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(Res.string.get_ready_to_unlock_your_fashion_potential),
                    color = MaterialTheme.appColors.color50,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onEvent(SplashScreenEvent.Navigate(SplashScreenDestination.OnboardingScreen))
                    },
                    modifier = Modifier.button()
                ) {
                    Text(
                        text = stringResource(Res.string.get_started),
                        fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = stringResource(Res.string.get_started),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ImageCardRow(
    resources: List<DrawableResource>,
    modifier: Modifier,
    itemModifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        resources.forEach { resource ->
            ImageCard(resource = resource, modifier = itemModifier)
        }
    }
}

@Composable
private fun ImageCard(resource: DrawableResource, modifier: Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(24.dp)) {
        Image(
            painter = painterResource(resource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}