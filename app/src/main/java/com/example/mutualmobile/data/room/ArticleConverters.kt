package com.example.mutualmobile.data.room

import androidx.room.TypeConverter
import com.example.mutualmobile.data.models.Article
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ArticleConverters {
    @TypeConverter
    fun stringToArticles(data: String): List<Article> =
        Json.decodeFromString<List<Article>>(data)

    @TypeConverter
    fun listToString(article: List<Article>): String =
        Json.encodeToString(article)
}