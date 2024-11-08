package com.fondstore.auth.data.mappers

import com.fondstore.auth.data.local.AuthTokensObject
import com.fondstore.auth.data.remote.responses.ResetPasswordErrorResponse
import com.fondstore.auth.data.remote.responses.SignInResponse
import com.fondstore.auth.data.remote.responses.SignUpResponse
import com.fondstore.auth.domain.errors.ResetPasswordError
import com.fondstore.auth.domain.errors.SignInError
import com.fondstore.auth.domain.errors.SignUpError
import com.fondstore.auth.domain.models.AuthTokens
import com.fondstore.auth.domain.models.AuthTokensState
import com.fondstore.auth.domain.models.SignInInfo
import com.fondstore.auth.domain.models.SignUpInfo
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

fun SignInResponse.Success.toInfo(): SignInInfo {
    return SignInInfo(access = access, refresh = refresh)
}

fun SignInResponse.Error.toError(): SignInError {
    return SignInError(
        email = email.plus(detail).plus(detail).filter(String::isNotBlank).joinToString("\n"),
        password = password.joinToString("\n")
    )
}

fun SignUpResponse.Success.toInfo(): SignUpInfo {
    return SignUpInfo(id = id, username = username, email = email)
}

fun SignUpResponse.Error.toError(): SignUpError {
    return SignUpError(
        username = username.plus(detail).plus(detail).filter(String::isNotBlank).joinToString("\n"),
        email = email.joinToString("\n"),
        password = password.joinToString("\n"),
        confirmPassword = rePassword.joinToString("\n")
    )
}

fun ResetPasswordErrorResponse.toError(): ResetPasswordError {
    return ResetPasswordError(
        email = email.plus(detail).plus(detail).filter(String::isNotBlank).joinToString("\n")
    )
}