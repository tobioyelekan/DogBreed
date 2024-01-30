package com.tobioyelekan.dogbreed.data.allbreeds.di

import com.tobioyelekan.dogbreed.data.allbreeds.repository.DogBreedsRepository
import com.tobioyelekan.dogbreed.data.allbreeds.repository.DogBreedsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AllBreedsDataModule {
    @Binds
    fun bindsAllBreedRepository(
        impl: DogBreedsRepositoryImpl
    ): DogBreedsRepository
}