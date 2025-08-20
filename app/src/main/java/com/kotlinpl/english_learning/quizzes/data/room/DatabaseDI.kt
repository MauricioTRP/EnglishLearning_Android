package com.kotlinpl.english_learning.quizzes.data.room

import android.content.Context
import androidx.room.Room
import com.kotlinpl.english_learning.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseDI {
    @Provides
    fun providesDatabase(@ApplicationContext applicationContext: Context) : AppDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "English.db")
        .createFromAsset("quizz.db")
//        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
}