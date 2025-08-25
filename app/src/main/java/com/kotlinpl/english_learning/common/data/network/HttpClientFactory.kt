package com.kotlinpl.english_learning.common.data.network

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 * [HttpClientFactory] creates an HTTP Client to be used by retrofit
 * It handles authentication and authorization process to an specific
 * service
 *
 * @param authenticator is responsible for refreshing auth tokens
 * @param authorization is responsible of adding Bearer token on each request
 */
class HttpClientFactory @Inject constructor (
    private val authenticator: Authenticator,
    private val authorization: Interceptor
) {
    fun build() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .build()

                chain.proceed(request)
            }
            .addInterceptor(authorization)
            .authenticator(authenticator)
            .build()
    }
}