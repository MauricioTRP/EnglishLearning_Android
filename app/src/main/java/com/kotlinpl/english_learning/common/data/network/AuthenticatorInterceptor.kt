package com.kotlinpl.english_learning.common.data.network

import android.util.Log
import com.kotlinpl.english_learning.auth.data.service.AuthApiService
import com.kotlinpl.english_learning.common.domain.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

private const val AUTH_INTERCEPTOR_TAG = "AUTH_INTERCEPTOR"

class AuthenticatorInterceptor @Inject constructor (
    private val tokenProvider: TokenProvider,
    private val apiService: AuthApiService
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val authTokens = runBlocking { tokenProvider.getToken() }

        val updatedTokens = runBlocking {
            try {
                apiService.refreshToken(authTokens.refreshToken)
            } catch (e: Exception) {
                Log.d(AUTH_INTERCEPTOR_TAG, e.toString())
            }
        }

        runBlocking {
            tokenProvider.setToken()
        }
    }
}