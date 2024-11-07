package com.fondstore.product.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.core.presentation.LoadingAnimationBox
import com.fondstore.product.domain.models.Category
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Bold
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.no_categories
import fondstore.composeapp.generated.resources.view_all
import org.jetbrains.compose.resources.stringResource

@Composable
fun CategoriesShelf(
    name: String,
    description: String,
    isGettingCategories: Boolean,
    categories: List<Category>,
    onViewAll: (() -> Unit)? = null,
    onCategorySelected: (Category) -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Bold),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = description,
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            if (isGettingCategories) {
                Card(modifier = Modifier.fillMaxWidth().aspectRatio(1.2f)) {
                    LoadingAnimationBox()
                }
            } else if (categories.isEmpty()) {
                Card(modifier = Modifier.fillMaxWidth().aspectRatio(1.2f)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        NoProducts()
                    }
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    val pagerState = rememberPagerState {
                        categories.size
                    }
                    val contentPadding =
                        if (categories.size == 1) {
                            PaddingValues(0.dp)
                        } else {
                            when (pagerState.currentPage) {
                                0 -> PaddingValues(end = 20.dp)
                                categories.size - 1 -> PaddingValues(start = 20.dp)
                                else -> PaddingValues(horizontal = 20.dp)
                            }
                        }

                    val aspectRatio = if (categories.size == 1) 1.1f else 1.2f

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth().aspectRatio(aspectRatio),
                        contentPadding = contentPadding,
                        pageSpacing = 10.dp
                    ) { index ->
                        val category = categories[index]

                        CategoryCard(
                            category = category,
                            modifier = Modifier.fillMaxSize(),
                            onClick = {
                                onCategorySelected(category)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(0.75f)
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(
                                10.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            categories.forEachIndexed { index, _ ->
                                val containerColor =
                                    if (index == pagerState.currentPage) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                                    }

                                Card(
                                    modifier = Modifier.size(12.dp),
                                    shape = CircleShape,
                                    colors = CardDefaults.cardColors(containerColor = containerColor)
                                ) {

                                }
                            }
                        }
                    }

                    if (onViewAll != null) {
                        Spacer(modifier = Modifier.height(20.dp))

                        TextButton(
                            onClick = onViewAll,
                            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.appColors.color50)
                        ) {
                            Text(
                                text = stringResource(Res.string.view_all),
                                fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}