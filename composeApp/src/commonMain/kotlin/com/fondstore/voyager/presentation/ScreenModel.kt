package com.fondstore.voyager.presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.compose.currentKoinScope
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope

@Composable
inline fun <reified T : ScreenModel> Screen.koinRootScreenModel(
    qualifier: Qualifier? = null,
    scope: Scope = currentKoinScope(),
    noinline parameters: ParametersDefinition? = null
): T {
    return LocalNavigator.current?.root?.koinNavigatorScreenModel<T>(
        qualifier = qualifier,
        scope = scope,
        parameters = parameters
    ) ?: koinScreenModel<T>(qualifier = qualifier, scope = scope, parameters = parameters)
}

@Composable
inline fun <reified T : ScreenModel> Screen.koinParentScreenModel(
    qualifier: Qualifier? = null,
    scope: Scope = currentKoinScope(),
    noinline parameters: ParametersDefinition? = null
): T {
    return LocalNavigator.current?.parent?.koinNavigatorScreenModel<T>(
        qualifier = qualifier,
        scope = scope,
        parameters = parameters
    ) ?: koinScreenModel<T>(qualifier = qualifier, scope = scope, parameters = parameters)
}

@Composable
inline fun <reified T : ScreenModel> Screen.koinNavigatorScreenModel(
    qualifier: Qualifier? = null,
    scope: Scope = currentKoinScope(),
    noinline parameters: ParametersDefinition? = null
): T {
    return LocalNavigator.current?.koinNavigatorScreenModel<T>(
        qualifier = qualifier,
        scope = scope,
        parameters = parameters
    ) ?: koinScreenModel<T>(qualifier = qualifier, scope = scope, parameters = parameters)
}