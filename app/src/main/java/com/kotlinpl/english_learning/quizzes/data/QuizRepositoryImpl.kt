package com.kotlinpl.english_learning.quizzes.data

import com.kotlinpl.english_learning.quizzes.data.room.mappers.toDomain
import com.kotlinpl.english_learning.quizzes.domain.Question
import com.kotlinpl.english_learning.quizzes.domain.QuizRepository

class QuizRepositoryImpl(
    private val quizzesLocalDataSource: QuizzesLocalDataSource

) : QuizRepository {
    override suspend fun getQuestions(tags: List<String>, limit: Int): List<Question> {
        return quizzesLocalDataSource.getQuestionsByTags(tags, limit = 10).map { it.toDomain() }
    }

    override suspend fun submitAnswer(answer: String) {
        // TODO("Not yet implemented")
    }

    override suspend fun submitQuiz() {
        // TODO("Not yet implemented")
    }
}