package com.kotlinpl.english_learning

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlinpl.english_learning.quizzes.data.room.dao.QuestionDao
import com.kotlinpl.english_learning.quizzes.data.room.dao.QuestionOptionDao
import com.kotlinpl.english_learning.quizzes.data.room.dao.TagsDao
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionOptionEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.QuestionTagCrossRefEntity
import com.kotlinpl.english_learning.quizzes.data.room.entity.TagEntity

@Database(
    entities = [QuestionEntity::class, QuestionTagCrossRefEntity::class, TagEntity::class, QuestionOptionEntity::class],
    version = 1,
    exportSchema = true,

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao() : QuestionDao
    abstract fun tagsDao() : TagsDao
    abstract fun questionOptionDao() : QuestionOptionDao
}
