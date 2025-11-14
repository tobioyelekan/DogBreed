package com.tobioyelekan.dogbreed.allbreeds.di

import com.tobioyelekan.dogbreed.allbreeds.DogBreedsRepository
import com.tobioyelekan.dogbreed.allbreeds.repository.DogBreedsRepositoryImpl
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
