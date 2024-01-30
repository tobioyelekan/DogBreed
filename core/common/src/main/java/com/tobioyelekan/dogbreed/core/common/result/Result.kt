package com.tobioyelekan.dogbreed.core.common.result

sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure(val errorMessage: String) : Result<Nothing>
}