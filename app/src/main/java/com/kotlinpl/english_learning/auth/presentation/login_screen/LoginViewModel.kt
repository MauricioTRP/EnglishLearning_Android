package com.kotlinpl.english_learning.auth.presentation.login_screen

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import com.kotlinpl.english_learning.auth.domain.PASSWORD_MIN_LENGTH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val authRepository: AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(LoginUIState())
        private set

    /**
     * Function to trigger login request
     */
    fun login() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoggingIn = true) // Update UI State to show user Login Process

            val result = authRepository.login(
                email = uiState.email.text,
                password = uiState.password.text
            )

            uiState = uiState.copy(isLoggingIn = false)
        }
    }

    /**
     * UI Related Tasks
     */
    fun updatePasswordTextFieldValue(newPassword: TextFieldValue) {
        uiState = uiState.copy(password = newPassword)
        checkCanLogin()
    }

    fun updateEmailTextFieldValue(newEmail: TextFieldValue) {
        uiState = uiState.copy(email = newEmail)
        checkCanLogin()
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
    }

    private fun checkCanLogin() {
        uiState = if(Patterns.EMAIL_ADDRESS.matcher(uiState.email.text).matches() && uiState.password.text.length >= PASSWORD_MIN_LENGTH) {
            uiState.copy(canLogin = true)
        } else {
            uiState.copy(canLogin = false)
        }
    }
}

