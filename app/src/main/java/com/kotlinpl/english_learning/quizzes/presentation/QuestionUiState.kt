package com.kotlinpl.english_learning.quizzes.presentation

import com.kotlinpl.english_learning.quizzes.domain.Quiz

data class QuestionUiState(
    val isLoading: Boolean = true,
    val quizQuestion: Quiz? = null,
    val error: String? = null,
)
