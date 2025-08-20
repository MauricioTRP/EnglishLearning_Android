package com.kotlinpl.english_learning.quizzes.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * TODO: Not yet in usage.
 * File being used is `MainViewModel.kt`
 */
class QuizViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(QuestionUiState())
    val uiState: StateFlow<QuestionUiState> = _uiState.asStateFlow()

    suspend fun submitAnswer() {}
    suspend fun submitQuiz() {}
    suspend fun getQuiz() {}
}