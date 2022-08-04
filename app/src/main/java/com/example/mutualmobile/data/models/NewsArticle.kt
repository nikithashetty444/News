package com.example.mutualmobile.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsArticle(
    val status: String,
    val results: List<Article>
)

@Serializable
data class Article(
    val title: String? = null,
    val link: String? = null,
    val description: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null
)