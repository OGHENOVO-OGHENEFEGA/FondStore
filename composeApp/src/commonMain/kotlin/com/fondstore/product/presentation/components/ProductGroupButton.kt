package com.fondstore.product.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.product.domain.models.ProductGroup
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProductGroupButton(
    group: ProductGroup,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.groupButtonColors(isSelected)
    ) {
        Text(
            text = stringResource(group.title),
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
private fun ButtonDefaults.groupButtonColors(isSelected: Boolean = false): ButtonColors {
    return if (isSelected) {
        buttonColors()
    } else {
        buttonColors(
            containerColor = MaterialTheme.appColors.productGroupButtonContainerColor,
            contentColor = MaterialTheme.appColors.color50
        )
    }
}