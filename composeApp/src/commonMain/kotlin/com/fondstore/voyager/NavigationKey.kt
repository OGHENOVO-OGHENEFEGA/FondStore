package com.fondstore.voyager

import cafe.adriel.voyager.core.screen.Screen
import kotlin.reflect.KClass

sealed interface NavigationKey {
    data class Key(val key: String) : NavigationKey
    data class Klass(val klass: KClass<out Screen>) : NavigationKey
}