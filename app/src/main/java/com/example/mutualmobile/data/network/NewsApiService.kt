package com.example.mutualmobile.data.network

import com.example.mutualmobile.data.models.NewsArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("news")
    suspend fun getLatestNews(
        @Query("apikey") apiKey: String,
        @Query("country") country: String,
        @Query("q") searchText: String? = null
    ): Response<NewsArticle>

    @GET("sources")
    suspend fun getSourceNews(
        @Query("apikey") apiKey: String,
        @Query("country") country: String
    ): Response<NewsArticle>
}