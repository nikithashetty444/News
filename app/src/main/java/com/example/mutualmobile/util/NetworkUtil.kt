package com.example.mutualmobile.util

import com.example.mutualmobile.data.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <T> returnResponseFromApi(
   crossinline serviceRequest: suspend () -> Response<T>
): Flow<ApiResponse<T>> {
    return flow {
        kotlin.runCatching {
            serviceRequest()
        }.onSuccess {
            if (it.isSuccessful) {
                it.body()?.let {
                    emit(ApiResponse.Success(it))
                } ?: kotlin.run {
                    emit(ApiResponse.Failure(Throwable("No data found")))
                }
            } else {
                emit(ApiResponse.Failure(Throwable("Something went wrong")))
            }
        }. onFailure {
            emit(ApiResponse.Failure(it))
        }
    }
}