package com.kotlinpl.english_learning.quizzes.data

import android.content.Context
import androidx.room.Room
import com.kotlinpl.english_learning.AppDatabase
import com.kotlinpl.english_learning.quizzes.data.remote.service.QuizzesService
import com.kotlinpl.english_learning.quizzes.domain.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class QuizDependencies {
    @Binds
    abstract fun bindQuizRepository(quizRepositoryImpl: QuizRepositoryImpl) : QuizRepository

    companion object {
        @Provides
        @Singleton
        fun provideQuizzesService(retrofit: Retrofit) : QuizzesService {
            return retrofit.create(QuizzesService::class.java)
        }

        @Singleton
        @Provides
        fun providesDatabase(@ApplicationContext applicationContext: Context) : AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "English.db")
            // .createFromAsset("quizz.db")
            .build()

        @Singleton
        @Provides
        fun providesQuizItemDao(appDatabase: AppDatabase) = appDatabase.quizItemDao()

        @Singleton
        @Provides
        fun providesAnswerDao(appDatabase: AppDatabase) = appDatabase.answerDao()
    }

}