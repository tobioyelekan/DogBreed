package com.tobioyelekan.dogbreed.data.breeddetails.di

import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailRepository
import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailsRepositoryImpl
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