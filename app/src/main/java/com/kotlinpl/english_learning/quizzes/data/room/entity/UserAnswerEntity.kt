package com.kotlinpl.english_learning.quizzes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "student_answer")
data class UserAnswerEntity(
    /**
     * Primary key is default generated as UUID
     * and then will be sync when INTERNET connection is established later
     */
    @PrimaryKey(autoGenerate = false)
    val userAnswerId: String,
    val studentId: String,
    val questionId: String,
    val quizId: String,
    val selectedOptionId: String,
)
