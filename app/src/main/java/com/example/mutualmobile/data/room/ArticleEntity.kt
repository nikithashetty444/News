package com.example.mutualmobile.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "articles"
)
data class ArticleEntity(
    @PrimaryKey
    var id: Int? = null,
    val title: String? = null,
    val link: String? = null,
    val description: String? = null,
    val imageUrl: String? = null
)
