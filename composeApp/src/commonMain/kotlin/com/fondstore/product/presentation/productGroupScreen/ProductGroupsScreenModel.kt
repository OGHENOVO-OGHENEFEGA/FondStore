package com.fondstore.product.presentation.productGroupScreen

import cafe.adriel.voyager.core.model.StateScreenModel
import com.fondstore.product.domain.models.ProductGroup
import kotlinx.coroutines.flow.update

class ProductGroupsScreenModel(group: ProductGroup) :
    StateScreenModel<ProductGroupsScreenState>(ProductGroupsScreenState(selectedGroup = group)) {

    fun onEvent(event: ProductGroupsScreenEvent) {
        when (event) {
            is ProductGroupsScreenEvent.SelectGroup -> selectGroup(event.group)
            is ProductGroupsScreenEvent.ToggleProductFavouriteState -> {}
            is ProductGroupsScreenEvent.Navigate -> navigate(event.destination)
            ProductGroupsScreenEvent.ClearDestination -> clearDestination()
        }
    }

    private fun selectGroup(group: ProductGroup) {
        mutableState.update {
            it.copy(selectedGroup = group)
        }
    }

    private fun navigate(destination: ProductGroupsScreenDestination) {
        mutableState.update {
            it.copy(destination = destination)
        }
    }

    private fun clearDestination() {
        mutableState.update {
            it.copy(destination = null)
        }
    }
}