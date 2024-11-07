package com.fondstore.product.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.no_product
import org.jetbrains.compose.resources.stringResource

@Composable
fun NoProducts() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.LocalMall,
            contentDescription = stringResource(Res.string.no_product),
            tint = MaterialTheme.appColors.color50
        )

        Text(
            text = stringResource(Res.string.no_product),
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 16.sp
        )
    }
}