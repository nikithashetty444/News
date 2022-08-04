package com.example.mutualmobile.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.mutualmobile.data.network.ApiResponse
import com.example.mutualmobile.domain.NewsUseCase
import com.example.mutualmobile.presentation.NewsUiEvent
import com.example.mutualmobile.presentation.NewsUiState
import kotlinx.coroutines.launch

class NewsViewModel(
    private val useCase: NewsUseCase
) : BaseViewModel<NewsUiState, NewsUiEvent>(
    NewsUiState()
) {
    private fun getNewsArticles(newsType: String) {
        viewModelScope.launch {
            useCase.getNewsArticle(newsType)
                .collect {
                    when (it) {
                        is ApiResponse.Success -> {
                            postUiState(
                                newUiState = uiState.value.copy(
                                    data = it.data.results,
                                    isLoading = false,
                                    showErrorDialog = false
                                )
                            )
                        }
                        is ApiResponse.Failure -> {
                            postUiState(
                                newUiState = uiState.value.copy(
                                    showErrorDialog = true,
                                    isLoading = false
                                )
                            )
                        }
                    }
                }
        }
    }

    private fun searchNewsArticles(searchText: String) {
        viewModelScope.launch {
            useCase.searchArticle(searchText)
                .collect {
                    when (it) {
                        is ApiResponse.Success -> {
                            postUiState(
                                newUiState = uiState.value.copy(
                                    data = it.data.results,
                                    isLoading = false,
                                    showErrorDialog = false
                                )
                            )
                        }
                        is ApiResponse.Failure -> {
                            postUiState(
                                newUiState = uiState.value.copy(
                                    showErrorDialog = true,
                                    isLoading = false
                                )
                            )
                        }
                    }
                }
        }
    }

    override fun postUiEvent(event: NewsUiEvent) {
        when (event) {
            is NewsUiEvent.FetchNewsArticle -> {
                postUiState(
                    newUiState = uiState.value.copy(
                        isLoading = true
                    )
                )
                getNewsArticles(event.newsType)
            }
            is NewsUiEvent.SearchLatestNews -> {
                postUiState(
                    newUiState = uiState.value.copy(
                        isLoading = true
                    )
                )
                searchNewsArticles(event.searchText)
            }
        }
    }
}