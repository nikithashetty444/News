package com.example.mutualmobile.domain.repository

import com.example.mutualmobile.data.network.ApiResponse
import com.example.mutualmobile.data.models.NewsArticle
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    /**
     * Fetches news article from api
     * @param [newsType]: new/archive/sources
     * @return list of [NewsArticle]
     * */
    fun fetchNewsArticle(newsType: String): Flow<ApiResponse<NewsArticle>>

    /**
     * Fetches news article from api
     * @param [searchText]: search query param
     * @return list of [NewsArticle]
     * */
    fun searchNewsArticle(searchText: String): Flow<ApiResponse<NewsArticle>>
}