package com.fondstore.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

@Composable
fun fontFamilyResource(resource: FontResource) = FontFamily(Font(resource))