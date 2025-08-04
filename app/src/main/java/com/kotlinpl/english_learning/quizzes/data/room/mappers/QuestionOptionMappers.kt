package com.kotlinpl.english_learning.quizzes.data.room.mappers

import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionOptionEntity
import com.kotlinpl.english_learning.quizzes.domain.QuestionOption

fun QuestionOptionEntity.toDomain() : QuestionOption = QuestionOption(
    text = text,
    isCorrect = isCorrect,
    feedback = feedback,
    id = questionId,
)
