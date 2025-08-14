package com.kotlinpl.english_learning.quizzes.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionTagCrossRefEntity
import com.kotlinpl.english_learning.quizzes.data.room.relations.QuestionWithTag

/**
 * Synchronous queries block UI Thread. (Main Thread)
 */
@Dao
interface QuestionDao {

    // Query Questions given tags
    @Transaction
    @Query(
        """
            SELECT * FROM question WHERE questionId IN (SELECT questionId FROM question_tag
            JOIN tag ON tag.tagId = question_tag.tagId
            WHERE tagName IN (:tags)
            GROUP BY questionId)
            LIMIT :limit
        """
    )
    suspend fun getQuestionsByTags(tags: List<String>, limit: Int) : List<QuestionWithTag>

    @Query("SELECT * FROM question")
    suspend fun getQuestionsByQuiz() : List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertQuestions(vararg questions: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertQuestionTagCrossRef(vararg questionTagCrossRefEntity: QuestionTagCrossRefEntity)
}
