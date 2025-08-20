package com.kotlinpl.english_learning.quizzes.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.services.storage.internal.TestStorageUtil
import com.kotlinpl.english_learning.AppDatabase
import com.kotlinpl.english_learning.quizzes.data.room.dao.QuestionDao
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class QuestionEntityReadWriteTest {
    private lateinit var questionDao: QuestionDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java).build()
        questionDao = appDatabase.questionDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeQuestionAndReadInList() {
        val question: QuestionEntity
    }
}