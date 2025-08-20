package com.kotlinpl.english_learning.common.data.network

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Intercepts request to add Bearer Token
 */
class AuthenticationInterceptor () : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        TODO("Not yet implemented")
    }
}