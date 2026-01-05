package com.tobioyelekan.dogbreed.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val coroutineDispatcherModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
}
