package com.kotlinpl.english_learning.auth.presentation.register_screen

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import com.kotlinpl.english_learning.auth.domain.PASSWORD_MIN_LENGTH
import com.kotlinpl.english_learning.auth.domain.PasswordValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val authRepository: AuthRepository
) : ViewModel()  {
    var uiState by mutableStateOf(RegisterUIState())
        private set

    /**
     * Function to trigger register request
     */
    fun register() {
        viewModelScope.launch {
            uiState = uiState.copy(isRegistering = true)

            val result = authRepository.login(
                email = uiState.email.text,
                password = uiState.password.text
            )

            uiState = uiState.copy(isRegistering = false)
        }
    }

    /**
     * UI Related tasks
     */
    fun updatePasswordTextFieldValue(newPassword: TextFieldValue) {
        uiState = uiState.copy(
            password = newPassword,
            passwordValidationState = validatePassword(newPassword.text),
            canRegister = canRegister()
        )
    }

    fun updateEmailTextFieldValue(newEmail: TextFieldValue) {
        uiState = uiState.copy(
            email = newEmail,
            canRegister = canRegister()
        )
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
    }

    private fun canRegister() : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(uiState.email.text).matches() &&
                uiState.passwordValidationState.isValidPassword
    }

    private fun validatePassword(password: String) : PasswordValidationState {
        val hasMinLength = password.length >= PASSWORD_MIN_LENGTH
        val hasNumber = password.any { it.isDigit() }
        val hasLowerCase = password.any{ it.isLowerCase() }
        val hasUpperCase = password.any { it.isUpperCase() }

        return PasswordValidationState(
            hasMinLength = hasMinLength,
            hasNumber = hasNumber,
            hasLowerCaseCharacter = hasLowerCase,
            hasUpperCaseCharacter = hasUpperCase
        )
    }
}

