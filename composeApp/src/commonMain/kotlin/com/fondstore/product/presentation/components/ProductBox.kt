package com.fondstore.product.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
fun ProductBox(
    product: Product,
    modifier: Modifier = Modifier,
    isFavourite: Boolean = false,
    isFavouritesRequestLoading: Boolean = false,
    onFavouriteCardClicked: () -> Unit,
    onProductClicked: () -> Unit,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth().height(190.dp),
            border = BorderStroke(1.dp, MaterialTheme.appColors.color10),
            onClick = onProductClicked
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                SubcomposeAsyncImage(
                    model = product.image,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    loading = {
                        LoadingAnimationBox()
                    },
                    error = {
                        ProductImagePlaceholder()
                    },
                    modifier = Modifier.fillMaxSize()
                )

                FavouriteCard(
                    isFavourite = isFavourite,
                    modifier = Modifier.size(32.dp).align(Alignment.BottomEnd)
                        .offset(x = (-10).dp, y = (-10).dp),
                    isLoading = isFavouritesRequestLoading,
                    contentPadding = PaddingValues(4.dp),
                    yOffSet = 1.dp,
                    onClick = onFavouriteCardClicked
                )
            }
        }

        Text(
            text = product.name,
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = product.price,
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Bold),
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}