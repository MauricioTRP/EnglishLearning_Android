package com.kotlinpl.english_learning.auth.data.dto

import kotlinx.serialization.Serializable


/**
 * Register Data Transfer Object
 *
 * Further validations must be realized on View Layer
 */
@Serializable
data class RegisterRequestDto(
    val email: String,
    val password: String,
    val acceptTerms: Boolean,
)
