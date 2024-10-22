package com.fondstore.auth.data.mappers

import com.fondstore.auth.data.local.AuthTokensObject
import com.fondstore.auth.domain.models.AuthTokens
import com.fondstore.auth.domain.models.AuthTokensState
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.query.RealmResults

fun ResultsChange<AuthTokensObject>.toTokens(): AuthTokens? {
    return list.toTokens()
}

fun RealmResults<AuthTokensObject>.toTokens(): AuthTokens? {
    return firstOrNull()?.toTokens()
}

private fun AuthTokensObject.toTokens(): AuthTokens {
    return AuthTokens(access = access, refresh = refresh, state = AuthTokensState.valueOf(state))
}