package com.fondstore.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Bold
import fondstore.composeapp.generated.resources.DMSans_SemiBold
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.done
import fondstore.composeapp.generated.resources.error
import fondstore.composeapp.generated.resources.success
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun InfoDialog(
    isError: Boolean = false,
    title: String = if (isError) stringResource(Res.string.error) else stringResource(Res.string.success),
    message: String? = null,
    actionMessage: String = stringResource(Res.string.done),
    onAction: () -> Unit
) {
    Dialog(
        onDismissRequest = onAction,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    val drawableResource = if (isError) Res.drawable.error else Res.drawable.success

                    Image(
                        painter = painterResource(drawableResource),
                        contentDescription = title,
                        modifier = Modifier.size(75.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Bold),
                        textAlign = TextAlign.Center
                    )

                    if (message != null) {
                        Text(
                            text = message,
                            color = MaterialTheme.appColors.color50,
                            fontSize = 16.sp,
                            fontFamily = fontFamilyResource(Res.font.DMSans_SemiBold),
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = onAction,
                        modifier = Modifier.button(),
                        shape = CircleShape
                    ) {
                        Text(
                            text = actionMessage,
                            fontSize = 16.sp,
                            fontFamily = fontFamilyResource(Res.font.DMSans_SemiBold),
                        )
                    }
                }
            }
        }
    }
}