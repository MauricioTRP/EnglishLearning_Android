package com.kotlinpl.english_learning.auth.presentation.register_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val authRepository: AuthRepository
) : ViewModel() {
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
        uiState = uiState.copy(password = newPassword)
    }

    fun updateEmailTextFieldValue(newEmail: TextFieldValue) {
        uiState = uiState.copy(email = newEmail)
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
    }
}