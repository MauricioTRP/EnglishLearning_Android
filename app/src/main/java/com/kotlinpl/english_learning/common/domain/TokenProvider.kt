package com.kotlinpl.english_learning.common.domain

/**
 * Token provider for any service used by the app
 */
interface TokenProvider {
    suspend fun getToken(): AuthTokens
    suspend fun setToken(authTokens: AuthTokens)
}