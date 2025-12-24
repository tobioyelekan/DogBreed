package com.tobioyelekan.dogbreed.feature.breedDetails.di

import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailRepository
import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BreedDetailsDataModule {
    @Binds
    fun bindsBreedDetailsRepository(
        impl: DogBreedDetailsRepositoryImpl
    ): DogBreedDetailRepository
}