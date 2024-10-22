package com.fondstore.account

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.account
import org.jetbrains.compose.resources.stringResource

class AccountScreenTab : Tab {

    override val options: TabOptions
        @Composable get()  {
            return TabOptions(index = 4u, title = stringResource(Res.string.account))
        }

    @Composable
    override fun Content() {
        AccountScreenContent()
    }
}