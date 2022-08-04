package com.example.mutualmobile.domain

import com.example.mutualmobile.data.network.ApiResponse
import com.example.mutualmobile.data.models.NewsArticle
import com.example.mutualmobile.domain.repository.INewsRepository
import com.example.mutualmobile.data.room.ArticleDao
import kotlinx.coroutines.flow.Flow

class NewsUseCase(
    private val repository: INewsRepository,
    private val articleDatabase: ArticleDao
) {
    fun getNewsArticle(newsType: String): Flow<ApiResponse<NewsArticle>> {
        return repository.fetchNewsArticle(newsType)
    }

    fun searchArticle(searchText: String): Flow<ApiResponse<NewsArticle>> {
        return repository.searchNewsArticle(searchText)
    }
}