package com.fondstore.favourites.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.ProductImagePlaceholder
import com.fondstore.core.presentation.numbClickable
import com.fondstore.product.domain.models.Product
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.remove_from_favourites
import org.jetbrains.compose.resources.stringResource

@Composable
fun FavouriteProduct(
    product: Product,
    isFavouriteRequestLoading: Boolean,
    onRemoveProduct: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        onClick = onClick,
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Row(modifier = modifier) {
            Surface(shape = CardDefaults.shape) {
                SubcomposeAsyncImage(
                    model = product.image,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(90.dp).fillMaxHeight(),
                    loading = {
                        LoadingAnimationBox()
                    },
                    error = {
                        ProductImagePlaceholder()
                    }
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = product.name,
                    color = MaterialTheme.appColors.color100,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = product.price,
                    color = MaterialTheme.appColors.color100,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                if (isFavouriteRequestLoading) {
                    CircleLoadingAnimation(
                        modifier = Modifier.size(12.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(Res.string.remove_from_favourites),
                        tint = MaterialTheme.appColors.color30,
                        modifier = Modifier.numbClickable(onClick = onRemoveProduct)
                    )
                }
            }
        }
    }
}
