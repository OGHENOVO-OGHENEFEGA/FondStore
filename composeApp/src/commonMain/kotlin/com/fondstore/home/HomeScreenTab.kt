package com.fondstore.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.home
import org.jetbrains.compose.resources.stringResource

object HomeScreenTab : Tab {

    override val options: TabOptions
        @Composable get() {
            return TabOptions(index = 0u, title = stringResource(Res.string.home))
        }

    @Composable
    override fun Content() {

    }
}