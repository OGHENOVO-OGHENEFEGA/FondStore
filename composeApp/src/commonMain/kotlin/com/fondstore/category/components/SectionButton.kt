package com.fondstore.category.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.product.domain.models.Section
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res

@Composable
fun SectionButton(
    section: Section?,
    isLoading: Boolean,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    if (isLoading || section == null) {
        val loadingAnimationColor = if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.appColors.color50
        }

        Button(
            onClick = {

            },
            modifier = modifier,
            colors = ButtonDefaults.sectionButtonColors(isSelected)
        ) {
            if (isLoading) {
                CircleLoadingAnimation(
                    modifier = Modifier.size(12.dp),
                    color = loadingAnimationColor,
                    strokeWidth = 2.dp
                )
            }
        }
    } else {
        Button(
            onClick = onClick,
            modifier = modifier,
            colors = ButtonDefaults.sectionButtonColors(isSelected)
        ) {
            Text(
                text = section.name,
                fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
private fun ButtonDefaults.sectionButtonColors(isSelected: Boolean = false): ButtonColors {
    return if (isSelected) {
        buttonColors()
    } else {
        buttonColors(
            containerColor = MaterialTheme.appColors.sectionButtonContainerColor,
            contentColor = MaterialTheme.appColors.color50
        )
    }
}