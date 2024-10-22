package com.fondstore.voyager.presentation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.fondstore.resources.presentation.fontFamilyResource
import com.fondstore.ui.presentation.appColors
import fondstore.composeapp.generated.resources.DMSans_SemiBold
import fondstore.composeapp.generated.resources.Res

@Composable
fun RowScope.NavigationBarTabItem(tab: Tab) {
    val navigator = LocalTabNavigator.current

    val isTabSelected = navigator.current == tab

    val selectedColor = MaterialTheme.colorScheme.primary
    val unSelectedColor = MaterialTheme.appColors.color50
    val color = if (isTabSelected) selectedColor else unSelectedColor

    NavigationBarItem(
        selected = isTabSelected,
        onClick = {
            navigator.current = tab
        },
        icon = {
            tabImage(tab = tab, tint = color)
        },
        label = {
            Text(
                text = tab.options.title,
                color = color,
                fontSize = 11.sp,
                fontFamily = fontFamilyResource(Res.font.DMSans_SemiBold),
                textAlign = TextAlign.Center
            )
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

@Composable
fun NavigationRailTabItem(tab: Tab) {
    val navigator = LocalTabNavigator.current

    val isTabSelected = navigator.current == tab

    val selectedColor = MaterialTheme.colorScheme.primary
    val unSelectedColor = MaterialTheme.appColors.color50
    val color = if (isTabSelected) selectedColor else unSelectedColor

    NavigationRailItem(
        selected = isTabSelected,
        onClick = {
            navigator.current = tab
        },
        icon = {
            tabImage(tab = tab, tint = color)
        },
        label = {
            Text(
                text = tab.options.title,
                color = color,
                fontSize = 11.sp,
                fontFamily = fontFamilyResource(Res.font.DMSans_SemiBold),
                textAlign = TextAlign.Center
            )
        },
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            indicatorColor = Color.Transparent,
            unselectedIconColor = MaterialTheme.appColors.color50,
            unselectedTextColor = MaterialTheme.appColors.color50
        )
    )
}

@Composable
fun tabImage(tab: Tab, tint: Color) {
//    val path = when (tab) {
//        HomeScreenTab -> DrawablePaths.HOME
//        PaymentScreenTab -> DrawablePaths.PAYMENT
//        WalletScreenTab -> DrawablePaths.WALLET
//        VirtualCardsScreenTab -> DrawablePaths.CARDS
//        ProfileScreenTab -> DrawablePaths.PROFILE
//        else -> ""
//    }
//
//    if (path.isNotBlank()) {
//        AsyncImage(
//            model = Res.getUri(path),
//            contentDescription = tab.options.title,
//            modifier = Modifier.size(24.dp),
//            contentScale = ContentScale.Crop,
//            colorFilter = ColorFilter.tint(tint)
//        )
//    }
}