package com.kotlinpl.english_learning.quizzes.data

import com.kotlinpl.english_learning.quizzes.data.room.dao.QuestionDao
import com.kotlinpl.english_learning.quizzes.data.room.dao.TagsDao

class QuizzesLocalDataSource(
    private val questionDao: QuestionDao,
    private val tagsDao: TagsDao
) {
    suspend fun getQuestionsByTags(tags: List<String>, limit: Int) = questionDao.getQuestionsByTags(tags, limit)
    suspend fun getTags() = tagsDao.getAll()
    suspend fun getQuestions() = questionDao.getQuestionsByQuiz()
}
