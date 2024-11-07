package com.fondstore.category.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fondstore.product.domain.models.Subcategory

@Composable
fun SubcategoryRow(
    isSubcategoriesRequestLoading: Boolean,
    subcategories: List<Subcategory>,
    selectedSubcategory: Subcategory?,
    itemWidth: Dp,
    onSubcategorySelected: (Subcategory) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center
    ) {
        if (subcategories.isEmpty()) {
            repeat(2) { index ->
                SubcategoryBox(
                    subcategory = null,
                    isLoading = isSubcategoriesRequestLoading,
                    isSelected = index == 0,
                    modifier = Modifier.width(itemWidth)
                ) {

                }
            }
        } else {
            subcategories.forEach { subcategory ->
                val isSelected = subcategory.id == selectedSubcategory?.id

                SubcategoryBox(
                    subcategory = subcategory,
                    isLoading = isSubcategoriesRequestLoading,
                    isSelected = isSelected,
                    modifier = Modifier.widthIn(min = 150.dp)
                ) {
                    onSubcategorySelected(subcategory)
                }
            }
        }
    }
}