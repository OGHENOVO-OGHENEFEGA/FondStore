package com.fondstore.voyager

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

val Tab.isSelected @Composable get() = LocalTabNavigator.current.current == this