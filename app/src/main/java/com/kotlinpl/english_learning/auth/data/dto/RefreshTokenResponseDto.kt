package com.kotlinpl.english_learning.auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseDto(
    val accessToken: String,
    val refreshToken: String
)
