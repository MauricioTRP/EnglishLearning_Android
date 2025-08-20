package com.kotlinpl.english_learning.quizzes.domain

data class UserAnswers(
    val userId: String,
    val quiz: Quiz,
    val answers: QuestionOption
)
