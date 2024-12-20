package com.fondstore.voyager

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.currentKoinScope
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope

@Composable
inline fun <reified T : ScreenModel> Screen.koinNavigatorScreenModel(
    navigator: Navigator? = LocalNavigator.current,
    qualifier: Qualifier? = null,
    scope: Scope = currentKoinScope(),
    noinline parameters: ParametersDefinition? = null,
): T {
    return navigator?.koinNavigatorScreenModel<T>(
        qualifier = qualifier,
        scope = scope,
        parameters = parameters
    ) ?: koinScreenModel<T>(qualifier = qualifier, scope = scope, parameters = parameters)
}

@Composable
inline fun <reified T : ScreenModel> Screen.koinRootScreenModel(
    qualifier: Qualifier? = null,
    scope: Scope = currentKoinScope(),
    noinline parameters: ParametersDefinition? = null,
): T {
    return koinNavigatorScreenModel(
        navigator = LocalNavigator.current?.root,
        qualifier = qualifier,
        scope = scope,
        parameters = parameters
    )
}