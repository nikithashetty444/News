package com.example.mutualmobile.usecase

import com.example.mutualmobile.data.network.ApiResponse
import com.example.mutualmobile.data.models.Article
import com.example.mutualmobile.data.models.NewsArticle
import com.example.mutualmobile.data.room.ArticleDao
import com.example.mutualmobile.domain.NewsUseCase
import com.example.mutualmobile.domain.repository.NewsRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito

class NewsUseCaseTest {

    private lateinit var subjectUnderTest: NewsUseCase

    @Mock
    private val mockNewsRepository = Mockito.mock(NewsRepository::class.java)
    @Mock
    private val mockArticleDatabase = Mockito.mock(ArticleDao::class.java)

    @Before
    fun setUp() {
        subjectUnderTest = NewsUseCase(mockNewsRepository, mockArticleDatabase)
    }

    @Test
    fun `test if getNewsArticle() returns Success`() {
        runTest {
            val mockNewsArticle = ApiResponse.Success<NewsArticle>(
                data = getMockNewsArticle()
            )
            Mockito.`when`(mockNewsRepository.fetchNewsArticle(anyString()))
                .thenReturn(
                    flow {
                        emit(mockNewsArticle)
                    }
                )

            val result = subjectUnderTest.getNewsArticle(anyString())

            verify(mockNewsRepository).fetchNewsArticle(anyString())
            result.collect {
                MatcherAssert.assertThat(it, CoreMatchers.instanceOf(ApiResponse.Success::class.java))
            }
        }
    }

    @Test
    fun `test if getNewsArticle() returns Failure`() {
        runTest {
            val mockFailure = ApiResponse.Failure<NewsArticle>(
                error = getMockFailure()
            )
            Mockito.`when`(mockNewsRepository.fetchNewsArticle(anyString()))
                .thenReturn(
                    flow {
                        emit(mockFailure)
                    }
                )

            val result = subjectUnderTest.getNewsArticle(anyString())

            verify(mockNewsRepository).fetchNewsArticle(anyString())
            result.collect {
                MatcherAssert.assertThat(it, CoreMatchers.instanceOf(ApiResponse.Failure::class.java))
            }
        }
    }

    @Test
    fun `test if searchArticle() returns Success`() {
        runTest {
            val mockNewsArticle = ApiResponse.Success<NewsArticle>(
                data = getMockNewsArticle()
            )
            Mockito.`when`(mockNewsRepository.searchNewsArticle(anyString()))
                .thenReturn(
                    flow {
                        emit(mockNewsArticle)
                    }
                )

            val result = subjectUnderTest.searchArticle(anyString())

            verify(mockNewsRepository).searchNewsArticle(anyString())
            result.collect {
                MatcherAssert.assertThat(it, CoreMatchers.instanceOf(ApiResponse.Success::class.java))
            }
        }
    }

    @Test
    fun `test if searchArticle() returns Failure`() {
        runTest {
            val mockFailure = ApiResponse.Failure<NewsArticle>(
                error = getMockFailure()
            )
            Mockito.`when`(mockNewsRepository.searchNewsArticle(anyString()))
                .thenReturn(
                    flow {
                        emit(mockFailure)
                    }
                )

            val result = subjectUnderTest.searchArticle(anyString())

            verify(mockNewsRepository).searchNewsArticle(anyString())
            result.collect {
                MatcherAssert.assertThat(it, CoreMatchers.instanceOf(ApiResponse.Failure::class.java))
            }
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