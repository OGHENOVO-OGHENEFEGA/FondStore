package com.fondstore.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_ExtraBold
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.app_icon
import fondstore.composeapp.generated.resources.fond
import fondstore.composeapp.generated.resources.ic_launcher_shared
import fondstore.composeapp.generated.resources.store
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TradeMark(
    iconSize: Dp = 24.dp,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(10.dp),
    mainFont: FontResource = Res.font.DMSans_ExtraBold,
    textSize: TextUnit = 24.sp
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_launcher_shared),
            contentDescription = stringResource(Res.string.app_icon),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(iconSize)
        )

        val appName = buildAnnotatedString {
            withStyle(SpanStyle(fontFamily = fontFamilyResource(mainFont))) {
                append(stringResource(Res.string.fond))
            }

            withStyle(SpanStyle(fontFamily = fontFamilyResource(Res.font.DMSans_Regular))) {
                append(stringResource(Res.string.store))
            }
        }

        Text(
            text = appName,
            color = MaterialTheme.appColors.color100,
            fontSize = textSize
        )
    }
}