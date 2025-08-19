package com.kotlinpl.english_learning.auth.data

import com.kotlinpl.english_learning.auth.data.dto.LoginResponseDto
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor (
//    private val httpClient : HttpClient
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): LoginResponseDto {
        TODO("Not yet implemented")
    }

    override suspend fun register(email: String, password: String) {
//        TODO("Not yet implemented")
    }
}