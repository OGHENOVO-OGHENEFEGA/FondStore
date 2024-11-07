package com.fondstore.category.categoryScreen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.category.components.SectionRow
import com.fondstore.category.components.SubcategoryRow
import com.fondstore.core.presentation.screenBackground
import com.fondstore.core.presentation.screenPadding
import com.fondstore.favourites.domain.models.FavouritesState
import com.fondstore.product.domain.models.ProductsState
import com.fondstore.product.presentation.components.ProductLazyColumn
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res

@Composable
fun CategoryScreenContent(
    state: CategoryScreenState,
    favouritesState: FavouritesState,
    onEvent: (CategoryScreenEvent) -> Unit,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize().screenBackground().screenPadding()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = state.category.name,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.appColors.color100,
                fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            SubcategoryRow(
                isSubcategoriesRequestLoading = state.isGettingSubcategories,
                subcategories = state.subCategoriesResult?.dataOrNull ?: listOf(),
                selectedSubcategory = state.selectedSubcategory,
                itemWidth = this@BoxWithConstraints.maxWidth.times(0.45f),
                onSubcategorySelected = { subcategory ->
                    onEvent(CategoryScreenEvent.SelectSubcategory(subcategory))
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            val sectionsPagerState = rememberPagerState {
                state.subCategoriesResult?.dataOrNull?.size ?: 0
            }

            LaunchedEffect(state.selectedSubcategory) {
                val selectedIndex =
                    state.subCategoriesResult?.dataOrNull?.indexOf(state.selectedSubcategory)
                        ?.coerceAtLeast(0)

                if (selectedIndex != null) {
                    sectionsPagerState.scrollToPage(selectedIndex)
                }
            }

            HorizontalPager(
                state = sectionsPagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false
            ) {
                val sections =
                    state.subcategorySectionsResultMap[state.selectedSubcategory]?.dataOrNull
                        ?: listOf()

                val selectedSection =
                    state.subcategoriesSelectedSectionMap[state.selectedSubcategory]

                Column(modifier = Modifier.fillMaxSize()) {
                    val isGettingSections =
                        state.subcategorySectionsLoadingList.contains(state.selectedSubcategory)

                    if (isGettingSections || sections.isNotEmpty()) {
                        SectionRow(
                            isGettingSections = isGettingSections,
                            sections = sections,
                            selectedSection = selectedSection,
                            onSectionSelected = { section ->
                                if (state.selectedSubcategory != null) {
                                    onEvent(
                                        CategoryScreenEvent.SelectSection(
                                            subcategory = state.selectedSubcategory,
                                            section = section
                                        )
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        val sectionItemsInfoPagerState = rememberPagerState { sections.size }

                        LaunchedEffect(selectedSection) {
                            val selectedSectionIndex =
                                sections.indexOf(selectedSection).coerceAtLeast(0)
                            sectionItemsInfoPagerState.scrollToPage(selectedSectionIndex)
                        }

                        HorizontalPager(
                            state = sectionItemsInfoPagerState,
                            modifier = Modifier.fillMaxSize(),
                            userScrollEnabled = false
                        ) {
                            val sectionItems =
                                state.sectionItemsResultMap[selectedSection]?.dataOrNull

                            ProductLazyColumn(
                                isLoadingProducts = state.sectionItemsLoadingList
                                    .contains(selectedSection),
                                products = sectionItems?.results ?: listOf(),
                                favourites = favouritesState.result?.dataOrNull ?: listOf(),
                                favouritesRequestLoadingList = favouritesState.requestLoadingList,
                                onToggleProductFavouritesState = { product ->
                                    onEvent(
                                        CategoryScreenEvent.ToggleProductFavouritesState(
                                            product = product
                                        )
                                    )
                                },
                                modifier = Modifier.fillMaxSize(),
                                isLoadingMoreProducts = state.nextSectionItemsLoadingList
                                    .contains(selectedSection),
                                onLoadMoreProducts = {
                                    if (selectedSection != null && sectionItems?.next != null) {
                                        onEvent(
                                            CategoryScreenEvent.GetNextSectionItems(
                                                section = selectedSection,
                                                url = sectionItems.next
                                            )
                                        )
                                    }
                                },
                                onProductSelected = { product ->
                                    onEvent(
                                        CategoryScreenEvent.Navigate(
                                            CategoryScreenDestination.ProductScreen(product = product)
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
