package com.kotlinpl.english_learning.quizzes.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.kotlinpl.english_learning.quizzes.domain.Quiz

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz WHERE quizId = :quizId")
    suspend fun getQuizById(quizId: String) : Quiz
}