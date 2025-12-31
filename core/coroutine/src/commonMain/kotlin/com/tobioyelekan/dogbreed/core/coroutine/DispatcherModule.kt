package com.tobioyelekan.dogbreed.core.coroutine

import org.koin.dsl.module

val coroutineDispatcherModule = module {
     single<CoroutineDispatcherProvider> { DefaultDispatcherProvider() }
}
