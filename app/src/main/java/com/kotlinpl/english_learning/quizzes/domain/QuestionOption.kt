package com.kotlinpl.english_learning.quizzes.domain

data class QuestionOption(
    val id: String,
    val text: String,
    val isCorrect: Boolean,
    val feedback: String? = null
)
