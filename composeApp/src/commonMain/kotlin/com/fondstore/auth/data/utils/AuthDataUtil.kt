package com.fondstore.auth.data.utils

object AuthDataUtil {
    private const val AUTH_URL = "auth"
    private const val USERS_URL = "$AUTH_URL/users"
    private const val JWT_URL = "$AUTH_URL/jwt"

    const val SIGN_IN_URL = "$JWT_URL/create/"
    const val SIGN_IN_TAG = "SIGN IN"

    const val SIGN_UP_URL = "$USERS_URL/"
    const val SIGN_UP_TAG = "SIGN UP"

    const val RESET_PASSWORD_URL = "$USERS_URL/reset_password/"
    const val RESET_PASSWORD_TAG = "RESET PASSWORD"

    const val DELETE_ACCOUNT_URL = "$USERS_URL/me/"
    const val DELETE_ACCOUNT_TAG = "DELETE ACCOUNT"

    const val AUTHORIZATION_KEY = "Authorization"
    fun getAuthorizationValue(token: String?) = "JWT $token"
}