package com.fondstore.voyager

import cafe.adriel.voyager.navigator.Navigator

val Navigator.root: Navigator?
    get() {
        var root: Navigator? = this

        while (root?.parent != null) {
            root = root.parent!!
        }

        return root
    }