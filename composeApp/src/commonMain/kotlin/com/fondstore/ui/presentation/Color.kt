package com.fondstore.ui.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF4385F5)
val SecondaryContainer = Primary.copy(alpha = 0.1f)
val SurfaceVariant = Primary.copy(alpha = 0.1f)

data class AppColors(
    val color100: Color = Color(0xFF0F172A),
    val searchTextFieldDisabledContainerColor: Color = Color(0xFFF1F1F1),
    val productGroupButtonContainerColor: Color = Color(0xFFF3F3F3),
    val sectionButtonContainerColor: Color = Color(0xFFF3F3F3),
    val subcategoryTextColor: Color = Color(0xFF7B7B7B)
) {
    val color70 get() = color100.copy(alpha = 0.7f)
    val color50 get() = color100.copy(alpha = 0.5f)
    val color33_5 get() = color100.copy(alpha = 0.335f)
    val color30 get() = color100.copy(alpha = 0.3f)
    val color10 get() = color100.copy(alpha = 0.1f)
}

val MaterialTheme.appColors: AppColors
    @Composable get() {
        return AppColors()
    }