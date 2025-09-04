package com.kotlinpl.english_learning.auth.data.dto

import com.kotlinpl.english_learning.common.data.network.dto.UserDataDto
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val success: Boolean,
    val data: LoginDataResponseDto
)

@Serializable
data class LoginDataResponseDto(
    val token: String,
    val refreshToken: String,
    val user: UserDataDto?
)