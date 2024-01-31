package com.tobioyelekan.dogbreed.core.common.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure(val errorMessage: String) : Result<Nothing>
}

fun <T, R> Flow<T>.mapToSuccess(transform: (T) -> R): Flow<Result<R>> {
    return map { value ->
        Result.Success(transform(value))
    }
}
