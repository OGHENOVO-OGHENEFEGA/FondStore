package com.fondstore.category.categoryScreen

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fondstore.error.Result
import com.fondstore.product.domain.models.Category
import com.fondstore.product.domain.models.Section
import com.fondstore.product.domain.models.Subcategory
import com.fondstore.product.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryScreenModel(category: Category, private val repository: ProductRepository) :
    StateScreenModel<CategoryScreenState>(CategoryScreenState(category = category)) {

    init {
        getSubcategories()
    }

    fun onEvent(event: CategoryScreenEvent) {
        when (event) {
            CategoryScreenEvent.GetSubcategories -> getSubcategories()
            is CategoryScreenEvent.SelectSubcategory -> selectSubcategory(event.subcategory)
            is CategoryScreenEvent.SelectSection -> {
                selectSection(event.subcategory, event.section)
            }

            is CategoryScreenEvent.GetNextSectionItems -> {
                getNextSectionItems(event.section, event.url)
            }

            is CategoryScreenEvent.ToggleProductFavouriteState -> {}
            is CategoryScreenEvent.Navigate -> navigate(event.destination)
            CategoryScreenEvent.ClearDestination -> clearDestination()
            CategoryScreenEvent.CloseScreen -> closeScreen()
        }
    }

    private fun getSubcategories() {
        screenModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Main) {
                mutableState.update {
                    it.copy(isGettingSubcategories = true)
                }
            }

            val result = repository.getSubcategories(categoryId = state.value.category.id)

            withContext(Dispatchers.Main + NonCancellable) {
                mutableState.update {
                    it.copy(isGettingSubcategories = false, subCategoriesResult = result)
                }

                if (result is Result.Success && result.data.isNotEmpty()) {
                    selectSubcategory(result.data.first())
                }
            }
        }
    }

    private fun selectSubcategory(subcategory: Subcategory) {
        mutableState.update {
            it.copy(selectedSubcategory = subcategory)
        }

        if (
            !state.value.subcategorySectionsLoadingList.contains(subcategory)
            && state.value.subcategorySectionsResultMap[subcategory] !is Result.Success
        ) {
            screenModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    mutableState.update {
                        val loadingList = it.subcategorySectionsLoadingList.toMutableList()
                        loadingList.add(subcategory)

                        it.copy(subcategorySectionsLoadingList = loadingList)
                    }
                }

                val result = repository.getSections(
                    categoryId = state.value.category.id,
                    subcategoryId = subcategory.id
                )

                withContext(Dispatchers.Main + NonCancellable) {
                    mutableState.update {
                        val loadingList = it.subcategorySectionsLoadingList.toMutableList()
                        loadingList.remove(subcategory)

                        val resultMap = it.subcategorySectionsResultMap.toMutableMap()
                        resultMap[subcategory] = result

                        it.copy(
                            subcategorySectionsLoadingList = loadingList,
                            subcategorySectionsResultMap = resultMap
                        )
                    }

                    if (result is Result.Success && result.data.isNotEmpty()) {
                        selectSection(subcategory = subcategory, section = result.data.first())
                    }
                }
            }
        }
    }

    private fun selectSection(subcategory: Subcategory, section: Section) {
        mutableState.update {
            val selectedSectionsMap = it.subcategoriesSelectedSectionMap.toMutableMap()
            selectedSectionsMap[subcategory] = section

            it.copy(subcategoriesSelectedSectionMap = selectedSectionsMap)
        }

        if (
            !state.value.sectionItemsLoadingList.contains(section)
            && state.value.sectionItemsResultMap[section] !is Result.Success
        ) {
            screenModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    mutableState.update {
                        val loadingList = it.sectionItemsLoadingList.toMutableList()
                        loadingList.add(section)

                        it.copy(sectionItemsLoadingList = loadingList)
                    }
                }

                val result = repository.getSectionItems(
                    categoryId = state.value.category.id,
                    subcategoryId = subcategory.id,
                    sectionId = section.id
                )

                withContext(Dispatchers.Main + NonCancellable) {
                    mutableState.update {
                        val loadingList = it.sectionItemsLoadingList.toMutableList()
                        loadingList.remove(section)

                        val resultMap = it.sectionItemsResultMap.toMutableMap()
                        resultMap[section] = result

                        it.copy(
                            sectionItemsLoadingList = loadingList,
                            sectionItemsResultMap = resultMap
                        )
                    }
                }
            }
        }
    }

    private fun getNextSectionItems(section: Section, url: String) {
        if (!state.value.nextSectionItemsLoadingList.contains(section)) {
            screenModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    mutableState.update {
                        val loadingList = it.nextSectionItemsLoadingList.toMutableList()
                        loadingList.add(section)

                        it.copy(nextSectionItemsLoadingList = loadingList)
                    }
                }

                val result = repository.getNextSectionItems(url = url)

                withContext(Dispatchers.Main + NonCancellable) {
                    mutableState.update {
                        val loadingList = it.nextSectionItemsLoadingList.toMutableList()
                        loadingList.remove(section)

                        val resultMap = it.sectionItemsResultMap.toMutableMap()

                        val currentResult = resultMap[section]

                        val newResult = if (result is Result.Success) {
                            when (currentResult) {
                                is Result.Success -> {
                                    currentResult.copy(
                                        currentResult.data.copy(
                                            count = result.data.count,
                                            next = result.data.next,
                                            previous = result.data.previous,
                                            results = currentResult.data.results.plus(result.data.results)
                                        )
                                    )
                                }

                                else -> result
                            }
                        } else currentResult

                        if (newResult != null) {
                            resultMap[section] = newResult
                        }

                        it.copy(
                            nextSectionItemsLoadingList = loadingList,
                            sectionItemsResultMap = resultMap
                        )
                    }
                }
            }
        }
    }

    private fun navigate(destination: CategoryScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }

    private fun clearDestination() {
        mutableState.update {
            it.copy(destination = null)
        }
    }

    private fun closeScreen() {
        mutableState.update {
            it.copy(isActive = false)
        }
    }
}