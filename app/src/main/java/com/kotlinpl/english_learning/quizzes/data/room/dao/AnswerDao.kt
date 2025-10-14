package com.kotlinpl.english_learning.quizzes.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.kotlinpl.english_learning.quizzes.data.room.entity.AnswersEntity

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: AnswersEntity)
}
