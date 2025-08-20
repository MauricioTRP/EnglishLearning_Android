package com.kotlinpl.english_learning.quizzes.domain

interface QuizRepository {
    suspend fun getQuestions(
        tags: List<String>,
        limit: Int
    ): List<Question>
    suspend fun submitAnswer(answer: String)
    suspend fun submitQuiz()
}
