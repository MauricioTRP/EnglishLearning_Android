package com.kotlinpl.english_learning.quizzes.data

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kotlinpl.english_learning.quizzes.data.remote.dto.AnswerDto
import com.kotlinpl.english_learning.quizzes.data.remote.service.QuizzesService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

/**
 *
 * [Check entry point explanation at Youtube](https://www.youtube.com/watch?v=-6WXLIOAO7E)
 */
@HiltWorker
class SyncWorker @AssistedInject constructor (
    @Assisted private val appContext: Context,
    @Assisted private val workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {
    companion object {
        const val KEY_QUIZ_ID = "KEY_QUIZ_ID"
        const val KEY_RESULT = "KEY_RESULT"
    }



    override suspend fun doWork(): Result {
        val quizId = inputData.getInt(KEY_QUIZ_ID, -1)
        val answer = inputData.getIntArray(KEY_RESULT) ?.toList()
        val entryPoint = EntryPointAccessors.fromApplication(appContext, SyncWorkerEntryPoint::class.java)
        val quizzesService = entryPoint.quizzesService()

        if (quizId == -1 || answer == null) {
            return Result.failure()
        }

        return try {
            quizzesService.solveQuiz(quizId, AnswerDto(answer))

            Result.success()
        } catch (e: Exception) {
            Result.retry() // HTTPClient will handle backoff strategy
        }
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SyncWorkerEntryPoint {
    fun quizzesService(): QuizzesService
}