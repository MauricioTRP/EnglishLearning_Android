package com.kotlinpl.english_learning.quizzes.domain

data class Question(
    val id: String?, // null if new
    val text: String,
    val options: List<QuestionOption>,
    val tags: List<String>,
)