package com.fondstore.category.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.core.presentation.defaultClickable
import com.fondstore.product.domain.models.Subcategory
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res

@Composable
fun SubcategoryBox(
    subcategory: Subcategory?,
    isLoading: Boolean,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val density = LocalDensity.current

    var columnWidth by remember {
        mutableStateOf(0.dp)
    }

    Column(
        modifier = modifier.defaultClickable(onClick = onClick).onSizeChanged {
            with(density) {
                columnWidth = it.width.toDp()
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
            if (isLoading) {
                CircleLoadingAnimation(modifier = Modifier.size(16.dp), strokeWidth = (2.5).dp)
            } else {
                val textColor = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.appColors.subcategoryTextColor
                }

                Text(
                    text = subcategory?.name ?: "",
                    color = textColor,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 16.sp
                )
            }
        }

        val containerColor =
            if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent

        val indicatorPadding = PaddingValues(horizontal = columnWidth.times(0.05f))

        Card(
            modifier = Modifier.width(columnWidth).height(4.dp).padding(indicatorPadding),
            colors = CardDefaults.cardColors(containerColor = containerColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            shape = RoundedCornerShape(topStartPercent = 100, topEndPercent = 100)
        ) {

        }

        if (!isSelected) {
            HorizontalDivider(
                modifier = Modifier.width(columnWidth).padding(indicatorPadding),
                thickness = 1.dp,
                color = MaterialTheme.appColors.color30
            )
        }
    }
}