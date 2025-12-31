package com.tobioyelekan.dogbreed.core.network

import com.tobioyelekan.dogbreed.core.network.api.DogBreedApiService
import org.koin.dsl.module

val networkModule = module {
    single { httpClient() }
    single<DogBreedApiService> { KtorDogBreedApiService(get()) }
}