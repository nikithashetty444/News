package com.example.mutualmobile.viewmodel

import MainDispatcherRule
import com.example.mutualmobile.data.network.ApiResponse
import com.example.mutualmobile.data.models.Article
import com.example.mutualmobile.data.models.NewsArticle
import com.example.mutualmobile.domain.NewsUseCase
import com.example.mutualmobile.presentation.NewsUiEvent
import com.example.mutualmobile.presentation.NewsUiState
import com.example.mutualmobile.presentation.viewmodel.NewsViewModel
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito

class NewsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var subjectUnderTest: NewsViewModel

    @Mock
    private var mockNewsUseCase = Mockito.mock(NewsUseCase::class.java)

    @Before
    fun setUp() {
        subjectUnderTest = NewsViewModel(mockNewsUseCase)
    }

    @Test
    fun `test if FetchNewsArticle event returns Success`() {
        runTest {
            val mockNewsArticle = ApiResponse.Success<NewsArticle>(
                data = getMockNewsArticle()
            )
            Mockito.`when`(mockNewsUseCase.getNewsArticle(anyString()))
                .thenReturn(
                    flow {
                        emit(mockNewsArticle)
                    }
                )

            subjectUnderTest.postUiEvent(NewsUiEvent.FetchNewsArticle(anyString()))

            val uiState = subjectUnderTest.uiState.value
            verify(mockNewsUseCase).getNewsArticle(anyString())
            MatcherAssert.assertThat(uiState, CoreMatchers.instanceOf(NewsUiState::class.java))
            assertEquals("Social", uiState.data[0].title)
            assertFalse(uiState.isLoading)
            assertFalse(uiState.showErrorDialog)
        }
    }

    @Test
    fun `test if FetchNewsArticle event returns Failure`() {
        runTest {
            val mockFailure = ApiResponse.Failure<NewsArticle>(
                error = getMockFailure()
            )
            Mockito.`when`(mockNewsUseCase.getNewsArticle(anyString()))
                .thenReturn(
                    flow {
                        emit(mockFailure)
                    }
                )

            subjectUnderTest.postUiEvent(NewsUiEvent.FetchNewsArticle(anyString()))

            val uiState = subjectUnderTest.uiState.value
            verify(mockNewsUseCase).getNewsArticle(anyString())
            MatcherAssert.assertThat(uiState, CoreMatchers.instanceOf(NewsUiState::class.java))
            assertFalse(uiState.isLoading)
            assertTrue(uiState.showErrorDialog)
        }
    }

    @Test
    fun `test if SearchLatestNews event returns Success`() {
        runTest {
            val mockNewsArticle = ApiResponse.Success<NewsArticle>(
                data = getMockNewsArticle()
            )
            Mockito.`when`(mockNewsUseCase.searchArticle(anyString()))
                .thenReturn(
                    flow {
                        emit(mockNewsArticle)
                    }
                )

            subjectUnderTest.postUiEvent(NewsUiEvent.SearchLatestNews(anyString()))

            val uiState = subjectUnderTest.uiState.value
            verify(mockNewsUseCase).searchArticle(anyString())
            MatcherAssert.assertThat(uiState, CoreMatchers.instanceOf(NewsUiState::class.java))
            assertEquals("Social", uiState.data[0].title)
            assertFalse(uiState.isLoading)
            assertFalse(uiState.showErrorDialog)
        }
    }

    @Test
    fun `test if SearchLatestNews event returns Failure`() {
        runTest {
            val mockFailure = ApiResponse.Failure<NewsArticle>(
                error = getMockFailure()
            )
            Mockito.`when`(mockNewsUseCase.searchArticle(anyString()))
                .thenReturn(
                    flow {
                        emit(mockFailure)
                    }
                )

            subjectUnderTest.postUiEvent(NewsUiEvent.SearchLatestNews(anyString()))

            val uiState = subjectUnderTest.uiState.value
            verify(mockNewsUseCase).searchArticle(anyString())
            MatcherAssert.assertThat(uiState, CoreMatchers.instanceOf(NewsUiState::class.java))
            assertFalse(uiState.isLoading)
            assertTrue(uiState.showErrorDialog)
        }
    }

    private fun getMockNewsArticle() = NewsArticle(
        status = "Success",
        results = listOf(
            Article(
                title = "Social",
                description = "Description"
            )
        )
    )

    private fun getMockFailure() = Throwable(message = "Error")
}