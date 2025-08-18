package com.kotlinpl.english_learning.auth.data.service

import com.kotlinpl.english_learning.auth.data.dto.LoginResponseDto
import retrofit2.http.POST

interface AuthApiService {
    @POST("/login")
    suspend fun login(email: String, password: String) : LoginResponseDto

    @POST("/register")
    suspend fun register(email: String, password: String)

    @POST("/refresh-token")
    suspend fun refreshToken(refreshToken: String)
}