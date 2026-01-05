package com.tobioyelekan.dogbreed.feature.subbreeds.di

import com.tobioyelekan.dogbreed.feature.subbreeds.repository.DogSubBreedRepository
import com.tobioyelekan.dogbreed.feature.subbreeds.repository.DogSubBreedRepositoryImpl
import org.koin.dsl.module

val subBreedDataModule = module {
    single<DogSubBreedRepository> {
        DogSubBreedRepositoryImpl(get())
    }
}