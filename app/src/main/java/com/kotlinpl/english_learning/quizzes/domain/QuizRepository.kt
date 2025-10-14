package com.kotlinpl.english_learning.quizzes.domain

import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getQuizItems(): Flow<List<Quiz>>
    suspend fun getQuizItemById(id: Int): Quiz
    suspend fun getCompletedQuizzes(): List<QuizCompleted>
    suspend fun submitAnswer(quizId: Int,answer: List<Int>): SolveFeedback
    suspend fun sync() : Boolean
}
