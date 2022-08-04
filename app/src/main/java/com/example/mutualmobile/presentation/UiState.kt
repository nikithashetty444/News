package com.example.mutualmobile.presentation

import com.example.mutualmobile.data.models.Article

interface UiState

interface BaseUiState<T>: UiState {
    val data: T
    val isLoading: Boolean
    val showErrorDialog: Boolean
}

data class NewsUiState(
    override val data: List<Article> = emptyList(),
    override val isLoading: Boolean = false,
    override val showErrorDialog: Boolean = false
): BaseUiState<List<Article>>
