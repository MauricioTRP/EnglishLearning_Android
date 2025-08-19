package com.kotlinpl.english_learning.quizzes.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlinpl.english_learning.quizzes.data.room.entity.TagEntity

@Dao
interface TagsDao {
    @Query("SELECT * FROM tag")
    suspend fun getAll() : List<TagEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTags(vararg tags: TagEntity)

    @Delete
    suspend fun deleteTags(vararg tags: TagEntity)
}
