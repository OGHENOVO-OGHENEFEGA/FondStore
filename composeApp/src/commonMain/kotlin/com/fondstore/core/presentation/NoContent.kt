package com.fondstore.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.resources.presentation.fontFamilyResource
import com.fondstore.ui.presentation.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res

@Composable
fun NoContent(
    heading: String,
    message: String,
    actionMessage: String,
    onAction: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(0.75f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = heading,
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = message,
            color = MaterialTheme.appColors.color50,
            fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onAction,
            modifier = Modifier.fillMaxWidth(0.5f).height(45.dp)
        ) {
            Text(
                text = actionMessage,
                fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                fontSize = 14.sp
            )
        }
    }
}