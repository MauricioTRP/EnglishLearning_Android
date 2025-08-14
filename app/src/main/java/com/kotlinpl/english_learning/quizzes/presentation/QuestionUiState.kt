package com.kotlinpl.english_learning.quizzes.presentation

import com.kotlinpl.english_learning.quizzes.domain.Question

data class QuestionUiState(
    val isLoading: Boolean = true,
    val quizQuestion: Question? = null,
    val error: String? = null,
)
