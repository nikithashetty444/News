package com.example.mutualmobile.data.network

sealed class ApiResponse<ResponseType> {
    data class Success<ResponseType>(val data: ResponseType): ApiResponse<ResponseType>()
    data class Failure<ResponseType>(val error: Throwable): ApiResponse<ResponseType>()
}
