package com.kotlinpl.english_learning.quizzes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlinpl.english_learning.quizzes.domain.QuestionOption

@Entity(tableName = "question_option")
data class QuestionOptionEntity(
    @PrimaryKey val questionOptionId: String,
    val questionId: String,
    val text: String,
    val isCorrect: Boolean,
    val feedback: String?
)

