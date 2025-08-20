package com.kotlinpl.english_learning.quizzes.data.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuizEntity

data class QuizWithQuestions(
    @Embedded val quiz: QuizEntity,
    @Relation(
        parentColumn = "quizId", // Parent Table key
        entityColumn = "quizId" // "Child" column key
    )
    val question: List<QuestionWithTag>
)
