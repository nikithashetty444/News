package com.example.mutualmobile.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(article: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
     fun getAllArticles(): Flow<List<ArticleEntity>>

}