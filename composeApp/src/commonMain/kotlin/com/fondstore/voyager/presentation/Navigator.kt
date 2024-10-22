package com.fondstore.voyager.presentation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

val Navigator.root: Navigator
    get() {
        var root = this

        while (true) {
            val parent = root.parent

            if (parent == null) {
                break
            } else {
                root = parent
            }
        }

        return root
    }

fun Navigator.popWhere(predicate: (Screen) -> Boolean) {
    val mutableItems = items.toMutableList()

    items.forEach { screen ->
        if (predicate(screen)) {
            mutableItems.remove(screen)
        }
    }

    replaceAll(items = mutableItems)
}