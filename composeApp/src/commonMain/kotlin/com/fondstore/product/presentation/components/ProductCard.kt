package com.fondstore.product.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.core.presentation.ProductImagePlaceholder
import com.fondstore.favourites.presentation.components.FavouriteCard
import com.fondstore.product.domain.models.Product
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Bold
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res

@Composable
fun ProductCard(
    product: Product,
    isFavourite: Boolean,
    isFavouritesStateChanging: Boolean,
    onFavouriteCardClicked: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier, onClick = onClick) {
        Box(modifier = Modifier.fillMaxSize()) {
            SubcomposeAsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                loading = {
                    LoadingAnimationBox()
                },
                error = {
                    ProductImagePlaceholder()
                }
            )

            FavouriteCard(
                isFavourite = isFavourite,
                modifier = Modifier.size(40.dp).align(Alignment.TopEnd)
                    .offset(x = (-18).dp, y = 18.dp),
                isLoading = isFavouritesStateChanging,
                contentPadding = PaddingValues(8.dp),
                yOffSet = 1.dp,
                onClick = onFavouriteCardClicked
            )

            Column(
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = 12.dp,
                        top = 12.dp,
                        end = 50.dp,
                        bottom = 12.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = product.name,
                    color = MaterialTheme.appColors.color100,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = product.price,
                    color = MaterialTheme.appColors.color100,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Bold),
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}