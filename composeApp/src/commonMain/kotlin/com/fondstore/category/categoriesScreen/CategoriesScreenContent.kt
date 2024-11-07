package com.fondstore.category.categoriesScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.BackNavTopAppBar
import com.fondstore.core.presentation.screenBackground
import com.fondstore.product.presentation.components.CategoryCard
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Bold
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.categories
import fondstore.composeapp.generated.resources.categories_description
import org.jetbrains.compose.resources.stringResource

@Composable
fun CategoriesScreenContent(
    state: CategoriesScreenState,
    onEvent: (CategoriesScreenEvent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackNavTopAppBar(
                onNavigateBack = {
                    onEvent(CategoriesScreenEvent.CloseScreen)
                },
                actions = {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = stringResource(Res.string.categories),
                        tint = MaterialTheme.appColors.color100
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().screenBackground().padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(Res.string.categories),
                        color = MaterialTheme.appColors.color100,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Bold),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(Res.string.categories_description),
                        color = MaterialTheme.appColors.color100,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }

            items(state.categories) { category ->
                CategoryCard(
                    category = category,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1.2f),
                    onClick = {
                        onEvent(
                            CategoriesScreenEvent.Navigate(
                                CategoriesScreenDestination.CategoryScreen(category)
                            )
                        )
                    }
                )
            }
        }
    }
}