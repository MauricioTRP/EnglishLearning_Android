package com.kotlinpl.english_learning.auth.domain

import com.kotlinpl.english_learning.auth.data.dto.LoginResponseDto
import com.kotlinpl.english_learning.auth.data.dto.RegisterResponseDto

interface AuthRepository {
    suspend fun login(email: String, password: String) : Result<LoginResponseDto>
    suspend fun register(email: String, password: String) : Result<RegisterResponseDto>
}