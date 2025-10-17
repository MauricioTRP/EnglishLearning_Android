package com.kotlinpl.english_learning.quizzes.data.room.mappers

import com.kotlinpl.english_learning.quizzes.data.room.entity.AnswersEntity

typealias QuizId = String
typealias OptionIX = Int

fun Pair<QuizId, OptionIX>.toAnswerEntity() : AnswersEntity {
    return AnswersEntity(
        id = 0, // Autogenerate should handle the Id creation, and expect this Id to be 0
        quizId = first.toInt(),
        optionIX = second
    )
}