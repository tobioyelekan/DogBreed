package com.tobioyelekan.dogbreed.feature.allbreeds.di

import com.tobioyelekan.dogbreed.feature.allbreeds.usecase.GetDogBreedListUseCase
import org.koin.dsl.module

val allBreedDomainModule = module {
    single { GetDogBreedListUseCase(get()) }
}