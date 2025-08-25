package com.kotlinpl.english_learning.common.data.network

import android.util.Log
import com.kotlinpl.english_learning.auth.data.service.AuthApiService
import com.kotlinpl.english_learning.common.domain.AuthTokens
import com.kotlinpl.english_learning.common.domain.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

private const val AUTH_INTERCEPTOR_TAG = "AUTH_INTERCEPTOR"

/**
 * [AuthenticatorInterceptor] is responsible to handle `401 HTTP Code` responses
 * of, in this case, [AuthApiService]. is a
 *
 * It updates stored [AuthTokens] via [TokenProvider]
 *
 * @property tokenProvider an implementation of [TokenProvider]
 * @property apiService a provider [Provider] of [AuthApiService]
 *
 * [Provider] is used to break [Circular Dependencies](https://docs.oracle.com/javaee/6/api/javax/inject/Provider.html).
 * Allowing to get an instance of [AuthApiService] in a lazy way
 *
 *
 */
class AuthenticatorInterceptor @Inject constructor (
    private val tokenProvider: TokenProvider,
    private val apiService: Provider<AuthApiService>
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val service = apiService.get()
        val authTokens = runBlocking { tokenProvider.getToken() }

        val refreshTokenResponse = runBlocking {
            try {
                service.refreshToken(authTokens?.refreshToken ?: "")
            } catch (e: Exception) {
                Log.d(AUTH_INTERCEPTOR_TAG, e.toString())
                null
            }
        }

        if (refreshTokenResponse != null && refreshTokenResponse.isSuccessful) {
            val updatedToken = refreshTokenResponse.body()
            if (updatedToken != null) {
                runBlocking {
                    tokenProvider.setToken(authTokens = AuthTokens(
                        accessToken = updatedToken.accessToken,
                        refreshToken = updatedToken.refreshToken
                    ))
                }

                return response.request.newBuilder()
                    .header("Authorization", "Bearer ${updatedToken.accessToken}")
                    .build()
            } else {
                // Response body null handling
                return null
            }
        } else {
            // Handle network error
            return null // Failed authentication
        }
    }
}