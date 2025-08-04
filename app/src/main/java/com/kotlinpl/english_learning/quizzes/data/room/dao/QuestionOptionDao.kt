package com.kotlinpl.english_learning.quizzes.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionOptionEntity

@Dao
interface QuestionOptionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertQuestionOptions(vararg questionOptions: QuestionOptionEntity)
}