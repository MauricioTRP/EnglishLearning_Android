package com.kotlinpl.english_learning.quizzes.data.remote.mappers

import com.kotlinpl.english_learning.quizzes.data.remote.dto.QuizDto
import com.kotlinpl.english_learning.quizzes.domain.Option
import com.kotlinpl.english_learning.quizzes.domain.Quiz

fun QuizDto.toDomain() : Quiz {
    return Quiz(
        id = this.id.toString(),
        title = this.title,
        text = this.text,
        options = this.options.mapIndexed { index, optionText -> Option(optionText = optionText, optionId = index.toString()) }
    )
}