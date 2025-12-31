package com.tobioyelekan.dogbreed.feature.breedDetails.di

import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailRepository
import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailsRepositoryImpl
import org.koin.dsl.module

val breedDetailsDataModule = module {
    single<DogBreedDetailRepository> {
        DogBreedDetailsRepositoryImpl(get())
    }
}