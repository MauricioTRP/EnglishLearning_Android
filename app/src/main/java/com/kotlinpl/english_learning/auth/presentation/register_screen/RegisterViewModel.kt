package com.kotlinpl.english_learning.auth.presentation.register_screen

import android.util.Log
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

            try {
                val result = authRepository.register(
                    email = uiState.email.text,
                    password = uiState.password.text
                )

                Log.d("Result Register payload", "")
            } catch (e: Error) {

            } finally {
                uiState = uiState.copy(isRegistering = false)
            }
        }
    }

    /**
     * UI Related tasks
     */
    fun updatePasswordTextFieldValue(newPassword: TextFieldValue) {
        val newPasswordValue = newPassword.text
        val newPasswordValidationState = validatePassword(newPasswordValue)
        val isValidNewPassword = newPasswordValidationState.isValidPassword

        uiState = uiState.copy(
            password = newPassword,
            passwordValidationState = newPasswordValidationState,
            canRegister = shouldAllowRegister(
                email = uiState.email.text, // Current email
                passwordIsValid = isValidNewPassword, // new password state
                acceptedTOS = uiState.acceptedTOS
            )
        )
    }

    fun updateEmailTextFieldValue(newEmail: TextFieldValue) {
        val newEmailValue = newEmail.text
        uiState = uiState.copy(
            email = newEmail,
            canRegister = shouldAllowRegister(
                email = newEmailValue, // Newly updated email
                passwordIsValid = uiState.passwordValidationState.isValidPassword, // current password state
                acceptedTOS = uiState.acceptedTOS
            )
        )
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
    }

    fun toggleTOS(isAccepted: Boolean) {
        uiState = uiState.copy(
            acceptedTOS = isAccepted,
            canRegister = shouldAllowRegister(
                email = uiState.email.text, // Current stored email
                passwordIsValid = uiState.passwordValidationState.isValidPassword, // Current password state
                acceptedTOS = isAccepted // Newly updated TOS this allows re rendering in UI
            )
        )
    }

    private fun shouldAllowRegister(
        email: String,
        passwordIsValid: Boolean,
        acceptedTOS: Boolean
    ) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                passwordIsValid &&
                acceptedTOS
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

