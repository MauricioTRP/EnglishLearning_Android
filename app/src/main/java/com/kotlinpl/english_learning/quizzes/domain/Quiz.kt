package com.kotlinpl.english_learning.quizzes.domain

import java.util.Date

data class Quiz(
    val id: String,
    val text: String,
    val title: String,
    val options: List<Option>
)

data class QuizCompleted(
    val id: String,
    val completedAt: Date
)