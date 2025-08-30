package com.kotlinpl.english_learning.common.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthDataDto (
    val token: String,
    val refreshToken: String
)