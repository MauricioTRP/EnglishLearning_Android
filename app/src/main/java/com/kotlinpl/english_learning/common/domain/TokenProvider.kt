package com.kotlinpl.english_learning.common.domain

/**
 * Token provider for any service used in the app
 */
interface TokenProvider {
    suspend fun getToken(): String
    suspend fun setToken(token: String)
}