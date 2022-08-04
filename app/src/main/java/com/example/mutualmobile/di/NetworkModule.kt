package com.example.mutualmobile.di

import com.example.mutualmobile.Constants
import com.example.mutualmobile.data.network.NewsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single {
        provideRetrofit()
    }
    single { provideNetworkApi(get()) }

}

val contentType = "application/json".toMediaType()
val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build())
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): NewsApiService =
    retrofit.create(NewsApiService::class.java)