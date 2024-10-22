package com.fondstore.app

import cafe.adriel.voyager.core.model.StateScreenModel

class AppScreenModel : StateScreenModel<AppState>(AppState()) {

    fun onEvent(event: AppEvent) {

    }
}