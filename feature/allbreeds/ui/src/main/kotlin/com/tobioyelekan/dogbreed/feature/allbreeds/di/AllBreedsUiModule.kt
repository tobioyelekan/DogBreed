package com.tobioyelekan.dogbreed.feature.allbreeds.di

import com.tobioyelekan.dogbreed.feature.allbreeds.repository.DogBreedsRepository
import com.tobioyelekan.dogbreed.feature.allbreeds.usecase.GetDogBreedListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AllBreedsUiModule {
    @Provides
    @Singleton
    fun provideGetDogBreedListUseCase(
        repository: DogBreedsRepository
    ): GetDogBreedListUseCase = GetDogBreedListUseCase(repository)
}