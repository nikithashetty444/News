package com.example.mutualmobile.presentation

interface UiEvent

sealed class NewsUiEvent(): UiEvent {
    data class FetchNewsArticle(val newsType: String): NewsUiEvent()
    data class SearchLatestNews(val searchText: String): NewsUiEvent()
}
