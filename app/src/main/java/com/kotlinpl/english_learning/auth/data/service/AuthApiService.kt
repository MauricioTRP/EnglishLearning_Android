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
    @POST("/auth/login")
    suspend fun login(
        @Body
        loginRequestDto: LoginRequestDto
    ) : Response<LoginResponseDto>

    @POST("/auth/register")
    suspend fun register(
        @Body
        registerRequestDto: RegisterRequestDto
    ) : Response<RegisterResponseDto>

    @POST("/auth/refresh-token")
    suspend fun refreshToken(refreshToken: String) : Response<RefreshTokenResponseDto>
}
