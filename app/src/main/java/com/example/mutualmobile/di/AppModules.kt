package com.example.mutualmobile.di

import androidx.room.Room
import com.example.mutualmobile.domain.repository.INewsRepository
import com.example.mutualmobile.domain.repository.NewsRepository
import com.example.mutualmobile.data.room.ArticleDao
import com.example.mutualmobile.data.room.ArticleDatabase
import com.example.mutualmobile.domain.NewsUseCase
import com.example.mutualmobile.presentation.viewmodel.NewsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { NewsViewModel(get()) }
    single { NewsUseCase(get(), get()) }
    single<INewsRepository> { NewsRepository(get()) }
    single {
        Room.databaseBuilder(
            androidApplication(),
            ArticleDatabase::class.java,
            "article_db.db"
        ).build()
    }
    single<ArticleDao> {
        val database = get<ArticleDatabase>()
        database.getArticleDao()
    }
}