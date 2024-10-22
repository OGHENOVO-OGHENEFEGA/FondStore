package com.fondstore.store.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import coil3.compose.AsyncImage
import com.fondstore.account.AccountScreenTab
import com.fondstore.core.presentation.TopAppBar
import com.fondstore.core.presentation.screenBackground
import com.fondstore.image.presentation.DrawablePaths
import com.fondstore.ui.presentation.appColors
import com.fondstore.voyager.presentation.NavigationBarTabItem
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.notifications
import fondstore.composeapp.generated.resources.search
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun StoreScreenContent(onEvent: (StoreScreenEvent) -> Unit) {
    TabNavigator(AccountScreenTab) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    actions = {
                        IconButton(
                            onClick = {
                                onEvent(StoreScreenEvent.Navigate(StoreScreenDestination.SearchScreen))
                            }
                        ) {
                            AsyncImage(
                                model = Res.getUri(DrawablePaths.SEARCH),
                                contentDescription = stringResource(Res.string.search),
                                modifier = Modifier.size(18.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.appColors.color100)
                            )
                        }

                        IconButton(
                            onClick = {
                                onEvent(StoreScreenEvent.Navigate(StoreScreenDestination.NotificationsScreen))
                            }
                        ) {
                            AsyncImage(
                                model = Res.getUri(DrawablePaths.NOTIFICATIONS),
                                contentDescription = stringResource(Res.string.notifications),
                                modifier = Modifier.size(width = 18.dp, height = 24.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.appColors.color100)
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(containerColor = MaterialTheme.colorScheme.onPrimary) {
                    NavigationBarTabItem(AccountScreenTab)
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().screenBackground().padding(innerPadding)) {
                CurrentTab()
            }
        }
    }
}