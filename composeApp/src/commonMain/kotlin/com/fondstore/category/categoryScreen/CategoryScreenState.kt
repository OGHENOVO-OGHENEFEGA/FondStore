package com.fondstore.category.categoryScreen

import com.fondstore.error.Result
import com.fondstore.product.domain.errors.SectionError
import com.fondstore.product.domain.errors.SectionItemsError
import com.fondstore.product.domain.errors.SubcategoryError
import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.models.Section
import com.fondstore.product.domain.models.SectionItems
import com.fondstore.product.domain.models.Subcategory

data class CategoryScreenState(
    val category: Category,
    val isGettingSubcategories: Boolean = false,
    val subCategoriesResult: Result<List<Subcategory>, SubcategoryError>? = null,
    val selectedSubcategory: Subcategory? = null,
    val subcategorySectionsLoadingList: List<Subcategory> = listOf(),
    val subcategorySectionsResultMap: Map<Subcategory, Result<List<Section>, SectionError>> = mapOf(),
    val subcategoriesSelectedSectionMap: Map<Subcategory, Section> = mapOf(),
    val sectionItemsLoadingList: List<Section> = listOf(),
    val nextSectionItemsLoadingList: List<Section> = listOf(),
    val sectionItemsResultMap: Map<Section, Result<SectionItems, SectionItemsError>> = mapOf(),
    val destination: CategoryScreenDestination? = null,
    val isActive: Boolean = true,
)
