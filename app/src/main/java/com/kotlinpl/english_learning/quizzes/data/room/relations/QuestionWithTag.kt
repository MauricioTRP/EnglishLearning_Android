package com.kotlinpl.english_learning.quizzes.data.room.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionOptionEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionTagCrossRefEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.TagEntity

data class QuestionWithTag(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "tagId",
        associateBy = Junction(QuestionTagCrossRefEntity::class)
    )
    val tags: List<TagEntity>,
    @Relation(
        parentColumn = "questionId",
        entityColumn = "questionId"
    )
    val options: List<QuestionOptionEntity>
)
