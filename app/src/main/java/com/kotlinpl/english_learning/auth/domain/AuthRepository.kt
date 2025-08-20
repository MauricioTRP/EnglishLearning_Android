package com.kotlinpl.english_learning.auth.domain

import com.kotlinpl.english_learning.auth.data.dto.LoginResponseDto

interface AuthRepository {
    suspend fun login(email: String, password: String) : LoginResponseDto
    suspend fun register(email: String, password: String)
}