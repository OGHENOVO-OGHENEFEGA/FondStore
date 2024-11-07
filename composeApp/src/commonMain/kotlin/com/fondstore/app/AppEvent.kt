package com.fondstore.app

sealed interface AppEvent {
    data object GetProducts : AppEvent
}