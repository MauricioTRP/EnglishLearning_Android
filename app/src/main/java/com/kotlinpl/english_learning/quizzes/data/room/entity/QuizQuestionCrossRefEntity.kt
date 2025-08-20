package com.kotlinpl.english_learning.quizzes.data.room.entity

import androidx.room.Entity

@Entity(
    tableName = "quiz_question",
    primaryKeys = ["quizId", "questionId"]
)
data class QuizQuestionCrossRefEntity(
    val quizId: String,
    val questionId: String
)
