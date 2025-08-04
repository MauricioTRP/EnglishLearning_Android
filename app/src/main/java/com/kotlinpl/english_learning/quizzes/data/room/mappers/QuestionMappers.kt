package com.kotlinpl.english_learning.quizzes.data.room.mappers

import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionEntity
import com.kotlinpl.english_learning.quizzes.data.room.relations.QuestionWithTag
import com.kotlinpl.english_learning.quizzes.domain.Question
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

fun QuestionWithTag.toDomain() : Question {
    val question = this.question
    val tags = this.tags.map { it.tagName }
    val options = this.options.map { it.toDomain() }

    return Question(
        id = question.questionId,
        text = question.text,
        options = options,
        tags = tags
    )
}
