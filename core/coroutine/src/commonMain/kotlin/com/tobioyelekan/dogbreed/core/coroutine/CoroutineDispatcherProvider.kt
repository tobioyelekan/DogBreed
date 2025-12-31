package com.tobioyelekan.dogbreed.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}
