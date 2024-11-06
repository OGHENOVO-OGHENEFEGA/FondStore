package com.fondstore.voyager.presentation

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