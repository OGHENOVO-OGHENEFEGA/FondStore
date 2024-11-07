package com.fondstore.product.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.ProductImagePlaceholder
import com.fondstore.product.domain.models.Category
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res

@Composable
fun CategoryCard(category: Category, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(modifier = modifier, onClick = onClick) {
        Box(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                model = category.image,
                contentDescription = category.name,
                contentScale = ContentScale.Crop,
                loading = {
                    LoadingAnimationBox()
                },
                error = {
                    ProductImagePlaceholder()
                },
                modifier = Modifier.fillMaxSize()
            )

            Text(
                text = category.name,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            MaterialTheme.appColors.color33_5
                        )
                    )
                )
                    .padding(top = 6.dp, bottom = 15.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
