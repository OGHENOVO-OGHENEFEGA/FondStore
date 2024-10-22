package com.fondstore.app

import cafe.adriel.voyager.core.model.StateScreenModel
import com.fondstore.product.domain.repositories.ProductRepository

class AppScreenModel(private val productRepository: ProductRepository) :
    StateScreenModel<AppState>(AppState()) {

    fun onEvent(event: AppEvent) {

    }
}