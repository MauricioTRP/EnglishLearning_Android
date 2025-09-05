package com.kotlinpl.english_learning.auth.data.dto

import kotlinx.serialization.Serializable

/**
 * Logout Data Transfer Object
 * should be passed in Body at part of backend request
 * as
 */
@Serializable
data class LogoutRequestDto(
   val refreshToken: String
)
