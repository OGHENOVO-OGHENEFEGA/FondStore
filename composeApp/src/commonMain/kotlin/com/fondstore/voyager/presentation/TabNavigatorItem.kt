package com.fondstore.voyager.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.fondstore.core.presentation.defaultClickable
import com.fondstore.ui.presentation.appColors

@Composable
fun RowScope.TabNavigationItem(
    tab: Tab,
    modifier: Modifier = Modifier.weight(1f)
) {
    val navigator = LocalTabNavigator.current

    val isTabSelected = navigator.current == tab

    Column(
        modifier = modifier.defaultClickable {
            navigator.current = tab
        },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val selectedTextColor = MaterialTheme.colorScheme.primary
        val unselectedTextColor = MaterialTheme.appColors.color100
        val textColor = if (isTabSelected) selectedTextColor else unselectedTextColor

        val selectedDividerColor = MaterialTheme.colorScheme.primary
        val unselectedDividerColor = MaterialTheme.appColors.color10
        val dividerColor = if (isTabSelected) selectedDividerColor else unselectedDividerColor

        Text(
            text = tab.options.title,
            color = textColor,
            style = MaterialTheme.typography.labelMedium
        )

        HorizontalDivider(color = dividerColor, thickness = (1.5).dp)
    }
}