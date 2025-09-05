package com.kotlinpl.english_learning.auth.data.service

import com.kotlinpl.english_learning.auth.data.dto.LoginRequestDto
import com.kotlinpl.english_learning.auth.data.dto.LoginResponseDto
import com.kotlinpl.english_learning.auth.data.dto.RefreshTokenResponseDto
import com.kotlinpl.english_learning.auth.data.dto.RegisterRequestDto
import com.kotlinpl.english_learning.auth.data.dto.RegisterResponseDto
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.Body

interface AuthApiService {
    // TODO: Check why is necessary to use /v1 if it's also set at ApiConfig
    @POST("/api/login")
    suspend fun login(
        @Body
        loginRequestDto: LoginRequestDto
    ) : Response<LoginResponseDto>

    @POST("/api/register")
    suspend fun register(
        @Body
        registerRequestDto: RegisterRequestDto
    ) : Response<RegisterResponseDto>

    @POST("/api/refresh-token")
    suspend fun refreshToken(refreshToken: String) : Response<RefreshTokenResponseDto>
}
