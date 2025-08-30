package com.kotlinpl.english_learning.common.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDataDto (
    val id: Int,
    val email: String,
)