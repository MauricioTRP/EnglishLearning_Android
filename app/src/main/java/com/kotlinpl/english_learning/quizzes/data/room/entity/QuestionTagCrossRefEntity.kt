package com.kotlinpl.english_learning.quizzes.data.room.entity

import androidx.room.Entity

@Entity(
    tableName = "question_tag",
    primaryKeys = ["questionId", "tagId"]
)
data class QuestionTagCrossRefEntity(
    val questionId: String,
    val tagId: String
)
