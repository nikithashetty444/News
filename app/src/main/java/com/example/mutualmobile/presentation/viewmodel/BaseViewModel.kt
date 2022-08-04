package com.example.mutualmobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mutualmobile.presentation.UiEvent
import com.example.mutualmobile.presentation.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<UiStateType : UiState, UiEventType : UiEvent>(
    initialState: UiStateType
): ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<UiStateType> = _uiState.asStateFlow()

    abstract fun postUiEvent(event: UiEventType)

    protected fun postUiState(newUiState: UiStateType) {
        _uiState.update {
            newUiState
        }
    }
}