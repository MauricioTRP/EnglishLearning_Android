package com.kotlinpl.english_learning

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.english_learning.common.domain.OnboardingChecker
import com.kotlinpl.english_learning.common.domain.TokenProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Class to handle if user is Logged In.
 * Or if user has taken the Onboarding
 */
@HiltViewModel
class MainViewModel @Inject constructor (
    private val tokenProvider: TokenProvider,
    private val onboardingChecker: OnboardingChecker
) : ViewModel()  {
    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isCheckingAuth = true)
            Log.d("MainViewModel", "Checking auth...")

            try {
                /**
                 * If there is a token stored on token provider, we can say that user is logged in
                 */
                val token = tokenProvider.getToken()
                Log.d("MainViewModel", "Token: ${token?.accessToken}")


                /**
                 * Check if user have done onboarding before
                 */
                val haveDoneOnboarding = onboardingChecker.getOnboardingFlag()

                state = state.copy(
                    isLoggedIn = !token?.accessToken.isNullOrEmpty(),
                    haveDoneOnboarding = haveDoneOnboarding.haveDoneOnboarding
                )

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                state = state.copy(isCheckingAuth = false)
            }
        }
    }
}