package com.kotlinpl.english_learning.common.data.network

import com.kotlinpl.english_learning.auth.data.service.AuthApiService
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

//class AuthenticationInterceptor(
//    authApiService: AuthApiService
//) : Authenticator {
//    override fun authenticate(route: Route?, response: Response): Request? {
//
//        return runBlocking {
//            val newToken = refresh
//        }
//    }
//
//    private suspend fun refreshToken()
//}