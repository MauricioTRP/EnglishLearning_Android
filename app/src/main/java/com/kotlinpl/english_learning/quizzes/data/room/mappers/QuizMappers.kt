package com.kotlinpl.english_learning.quizzes.data.room.mappers

import com.kotlinpl.english_learning.quizzes.data.room.relations.QuizWithQuestions
import com.kotlinpl.english_learning.quizzes.domain.Quiz

fun QuizWithQuestions.toDomain() : Quiz {
    val quiz = this.quiz
    val questions = this.question.map { it.toDomain() }

    return Quiz(
        id = quiz.quizId,
        title = quiz.title,
        description = quiz.description,
        questions = questions
    )
}