package com.tobioyelekan.dogbreed.feature.allbreeds.di

import com.tobioyelekan.dogbreed.feature.allbreeds.repository.DogBreedsRepository
import com.tobioyelekan.dogbreed.feature.allbreeds.repository.DogBreedsRepositoryImpl
import org.koin.dsl.module

val allBreedDataModule = module {
    single<DogBreedsRepository> {
        DogBreedsRepositoryImpl(get(), get())
    }
}