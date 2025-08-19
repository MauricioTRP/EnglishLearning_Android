package com.kotlinpl.english_learning.auth.presentation.login_screen

import androidx.compose.ui.text.input.TextFieldValue

data class LoginUIState(
    val email: TextFieldValue = TextFieldValue(),
    val password: TextFieldValue = TextFieldValue(),
    val isPasswordVisible: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false
)
