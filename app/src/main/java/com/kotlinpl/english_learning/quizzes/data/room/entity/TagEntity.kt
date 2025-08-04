package com.kotlinpl.english_learning.quizzes.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tag system be used to categorize questions.
 */
@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey val tagId: String,
    val tagName: String
)
