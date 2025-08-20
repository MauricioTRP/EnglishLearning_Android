package com.kotlinpl.english_learning.auth.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpirationTimestamp: Long, /* TimeStamp used to handle refresh token */
    val userId: String /* UUID associated to user */
)
