package com.fondstore.launcher.data.mappers

import com.fondstore.launcher.data.local.LauncherObject
import io.realm.kotlin.query.RealmResults
import com.fondstore.launcher.domain.domain.LauncherState

fun RealmResults<LauncherObject>.toState(): LauncherState? {
    return firstOrNull()?.toState()
}

private fun LauncherObject.toState() = LauncherState(isUserOnboard = isUserOnboard)