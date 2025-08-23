package com.kotlinpl.english_learning.auth.data.service

import com.kotlinpl.english_learning.auth.data.dto.LoginResponseDto
import com.kotlinpl.english_learning.auth.data.dto.RefreshTokenResponseDto
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Body

interface AuthApiService {
    @POST("/auth/login")
    suspend fun login(email: String, password: String) : Response<LoginResponseDto>

    @POST("/auth/register")
    suspend fun register(
        @Body
        email: String, password: String
    )

    @POST("/auth/refresh-token")
    suspend fun refreshToken(refreshToken: String) : Response<RefreshTokenResponseDto>
}
