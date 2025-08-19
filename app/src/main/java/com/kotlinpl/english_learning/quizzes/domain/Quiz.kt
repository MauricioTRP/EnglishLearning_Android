package com.kotlinpl.english_learning.quizzes.domain

data class Quiz(
    val id: String,
    val title: String,
    val description: String,
    val questions: List<Question>
)
