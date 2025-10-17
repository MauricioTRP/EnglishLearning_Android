package com.kotlinpl.english_learning.quizzes.domain

import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getQuizItems(): Flow<List<Quiz>>
    suspend fun getQuizItemById(id: String): Quiz
    suspend fun getCompletedQuizzes(): List<QuizCompleted>
    suspend fun submitAnswer(quizId: String,answer: List<Int>)
    suspend fun sync() : Boolean
}
