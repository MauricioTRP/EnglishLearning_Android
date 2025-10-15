package com.kotlinpl.english_learning.quizzes.presentation

import com.kotlinpl.english_learning.quizzes.domain.Quiz

data class QuizUiState(
    val isLoading: Boolean = true,
    val quizzes: List<Quiz> = emptyList(),
    val currentQuiz: Quiz? = null,
    val error: String? = null,
)
