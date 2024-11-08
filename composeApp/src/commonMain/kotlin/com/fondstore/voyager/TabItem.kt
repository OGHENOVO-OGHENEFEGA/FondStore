package com.fondstore.voyager

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import coil3.compose.AsyncImage
import com.fondstore.account.AccountScreenTab
import com.fondstore.cart.presentation.CartScreenTab
import com.fondstore.favourites.presentation.FavouritesScreenTab
import com.fondstore.home.HomeScreenTab
import com.fondstore.image.DrawablePaths
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RowScope.NavigationBarTabItem(tab: Tab) {
    val navigator = LocalTabNavigator.current

    val selectedColor = MaterialTheme.colorScheme.primary
    val unSelectedColor = MaterialTheme.appColors.color50
    val color = if (tab.isSelected) selectedColor else unSelectedColor

    NavigationBarItem(
        selected = tab.isSelected,
        onClick = {
            navigator.current = tab
        },
        icon = {
            val path = when (tab) {
                HomeScreenTab -> DrawablePaths.HOME
                FavouritesScreenTab -> DrawablePaths.FAVOURITE
                CartScreenTab -> DrawablePaths.CART
                AccountScreenTab -> DrawablePaths.PROFILE
                else -> ""
            }

            if (path.isNotBlank()) {
                AsyncImage(
                    model = Res.getUri(path),
                    contentDescription = tab.options.title,
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(color)
                )
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            indicatorColor = Color.Transparent,
            unselectedIconColor = MaterialTheme.appColors.color50,
            unselectedTextColor = MaterialTheme.appColors.color50
        )
    )
}