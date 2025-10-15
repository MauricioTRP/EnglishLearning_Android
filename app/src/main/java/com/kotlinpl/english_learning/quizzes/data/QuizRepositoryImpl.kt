package com.kotlinpl.english_learning.quizzes.data

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.kotlinpl.english_learning.quizzes.data.remote.mappers.toDomain
import com.kotlinpl.english_learning.quizzes.data.room.QuizzesLocalDataSource
import com.kotlinpl.english_learning.quizzes.data.remote.service.QuizzesService
import com.kotlinpl.english_learning.quizzes.domain.Quiz
import com.kotlinpl.english_learning.quizzes.domain.QuizRepository
import com.kotlinpl.english_learning.quizzes.domain.QuizCompleted
import com.kotlinpl.english_learning.quizzes.domain.SolveFeedback
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.sql.Date
import javax.inject.Inject

/**
 * An implementation of [QuizRepository]
 *
 * This repository is responsible for handling data operations for quizzes.
 *
 * @property quizzesLocalDataSource the local data source for quizzes.
 * @property quizzesRemoteDataSource the remote data source for quizzes.
 */
class QuizRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val quizzesLocalDataSource: QuizzesLocalDataSource,
    private val quizzesRemoteDataSource: QuizzesService
) : QuizRepository {

    /**
     * Retrieves a list of questions using an offline-first approach
     * Returns a flow that emits local data first, then updates with remote data when available
     * and emits again
     *
     */
    override suspend fun getQuizItems(): Flow<List<Quiz>> = flow {
        val cachedQuizzes = quizzesLocalDataSource.getQuizItems()
        emit(cachedQuizzes)
    }

    override suspend fun getQuizItemById(id: Int): Quiz {
        return quizzesLocalDataSource.getQuizById(id)
    }

    override suspend fun getCompletedQuizzes(): List<QuizCompleted> {
        return quizzesLocalDataSource.getCompletedQuizzes()
    }

    override suspend fun submitAnswer(
        quizId: Int,
        answer: List<Int>
    ): SolveFeedback {
        val quiz = quizzesLocalDataSource.getQuizById(quizId)
        val isCorrect = quiz.options.map { it.optionId.toInt() }.containsAll(answer)

        if (isCorrect) {
            val completedQuiz = QuizCompleted(
                id = quiz.id,
                completedAt = Date(System.currentTimeMillis())
            )

            // Queue a submission through a Worker.
            // backoff is handled by the Worker as defined in HTTPClient
            queueSubmissionSync(quizId, answer)

            return SolveFeedback(
                success = "Excelent Choice",
                feedback = "Well done"
            )
        } else {
            return SolveFeedback(
                success = "Wrong Choice",
                feedback = "Try again"
            )
        }
    }

    override suspend fun sync(): Boolean {
        Log.d("QuizRepositoryImpl", "sync called")
        return try {
            val remoteQuizzes = quizzesRemoteDataSource.getQuizzes().content.map { it.toDomain() }
            quizzesLocalDataSource.deleteAll()
            quizzesLocalDataSource.insertQuizzes(remoteQuizzes)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun queueSubmissionSync(quizId: Int, answer: List<Int>) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData = Data.Builder()
            .putInt(SyncWorker.KEY_QUIZ_ID, quizId)
            .putIntArray(SyncWorker.KEY_RESULT, answer.toIntArray())
            .build()

        val submissionWorkRequest = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(constraints)
            .setInputData(inputData)
            .build()

        WorkManager.getInstance(context).enqueue(submissionWorkRequest)
    }
}