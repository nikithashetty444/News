package com.example.mutualmobile.domain.repository

import com.example.mutualmobile.BuildConfig
import com.example.mutualmobile.Constants
import com.example.mutualmobile.data.network.ApiResponse
import com.example.mutualmobile.data.network.NewsApiService
import com.example.mutualmobile.data.models.NewsArticle
import com.example.mutualmobile.util.returnResponseFromApi
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val newsApiService: NewsApiService
): INewsRepository {

    override fun fetchNewsArticle(newsType: String): Flow<ApiResponse<NewsArticle>> {
       return when(newsType) {
           Constants.NEWS -> returnResponseFromApi {
               newsApiService.getLatestNews(
                   apiKey = BuildConfig.API_KEY,
                   country = "in",
               )
           }
           else -> returnResponseFromApi {
               newsApiService.getSourceNews(
                   apiKey = BuildConfig.API_KEY,
                   country = "in"
               )
           }
       }

    }

    override fun searchNewsArticle(searchText: String): Flow<ApiResponse<NewsArticle>> {
      return returnResponseFromApi {
            newsApiService.getLatestNews(
                apiKey = BuildConfig.API_KEY,
                country = "in",
                searchText = searchText
            )
        }
    }
}