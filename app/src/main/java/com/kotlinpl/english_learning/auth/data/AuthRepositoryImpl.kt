package com.kotlinpl.english_learning.auth.data

import com.kotlinpl.english_learning.auth.data.dto.LoginRequestDto
import com.kotlinpl.english_learning.auth.data.dto.LoginResponseDto
import com.kotlinpl.english_learning.auth.data.dto.RegisterRequestDto
import com.kotlinpl.english_learning.auth.data.service.AuthApiService
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor (
    private val authApiService: AuthApiService
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): LoginResponseDto {

        val response = authApiService.login(
            LoginRequestDto(email = email, password = password)
        )

        return LoginResponseDto(
            accessToken = "",
            refreshToken = "TODO()",
            accessTokenExpirationTimestamp = 321321,
            userId = "TODO()"
        )
    }

    override suspend fun register(email: String, password: String) {
        val response = authApiService.register(
            RegisterRequestDto(email = email, password = password)
        )
    }
}