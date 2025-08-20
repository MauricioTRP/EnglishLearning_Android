package com.kotlinpl.english_learning.quizzes.data.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.kotlinpl.english_learning.quizzes.domain.Quiz

@Entity(tableName = "quiz")
data class QuizEntity(
    @PrimaryKey val quizId: String,
    val title: String,
    val description: String
)



