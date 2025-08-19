package com.kotlinpl.english_learning.auth.presentation.register_screen

import androidx.compose.ui.text.input.TextFieldValue
import com.kotlinpl.english_learning.auth.domain.PasswordValidationState

data class RegisterUIState(
    val email: TextFieldValue = TextFieldValue(),
    val isEmailValid: Boolean = false,
    val password: TextFieldValue = TextFieldValue(),
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false
)