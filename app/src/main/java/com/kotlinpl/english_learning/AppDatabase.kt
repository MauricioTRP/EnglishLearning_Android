package com.kotlinpl.english_learning

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kotlinpl.english_learning.common.data.local_storage.RoomConverters
import com.kotlinpl.english_learning.quizzes.data.room.dao.AnswerDao
import com.kotlinpl.english_learning.quizzes.data.room.dao.QuizItemDao
import com.kotlinpl.english_learning.quizzes.data.room.entity.AnswersEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.CompletionsEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuizItemEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuizOptionsEntity

@Database(
    entities = [
        AnswersEntity::class,
        CompletionsEntity::class,
        QuizItemEntity::class,
        QuizOptionsEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun answerDao() : AnswerDao
    abstract fun quizItemDao() : QuizItemDao

}
