package com.kotlinpl.english_learning.quizzes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kotlinpl.english_learning.quizzes.domain.Question

@Entity(tableName = "question")
data class QuestionEntity(
    @PrimaryKey val questionId: String,
    val text: String,
)


