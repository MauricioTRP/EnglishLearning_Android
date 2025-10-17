package com.kotlinpl.english_learning.quizzes.data.room

import com.kotlinpl.english_learning.quizzes.data.room.dao.QuizItemDao
import com.kotlinpl.english_learning.quizzes.data.room.entity.CompletionsEntity
import com.kotlinpl.english_learning.quizzes.data.room.mappers.toDomain
import com.kotlinpl.english_learning.quizzes.data.room.mappers.toQuizWithOptions
import com.kotlinpl.english_learning.quizzes.domain.Quiz
import com.kotlinpl.english_learning.quizzes.domain.QuizCompleted
import java.util.Date
import javax.inject.Inject

typealias QuizId = Int
class QuizzesLocalDataSource @Inject constructor (
    private val quizItemDao: QuizItemDao
) {
    suspend fun getQuizItems() : List<Quiz> {
        return quizItemDao.getQuizWithOptions().map { it.toDomain() }
    }

    suspend fun getQuizById(id: String) : Quiz {
        return quizItemDao.getQuizWithOptionsById(id).toDomain()
    }

    suspend fun getCompletedQuizzes() : List<QuizCompleted> {
        return quizItemDao.getCompletedQuizzes().map { it.toDomain() }
    }

    suspend fun insertCompletions(completedQuizItems: List<Pair<QuizCompleted, QuizId>>) {
        val completions = completedQuizItems.map {
            val (quizCompleted, quizId) = it
            CompletionsEntity(
                id = quizCompleted.id.toInt(),
                quizItemId = quizId,
                completedAt = Date(System.currentTimeMillis())
            )
        }

        quizItemDao.insertCompletions(completions)
    }

    suspend fun insertQuizzes(quizzes: List<Quiz>) {
        quizItemDao.insertAllQuizzes(quizzes.map { it.toQuizWithOptions() })
    }

    suspend fun deleteAll() {
        quizItemDao.deleteAll()
    }
}
