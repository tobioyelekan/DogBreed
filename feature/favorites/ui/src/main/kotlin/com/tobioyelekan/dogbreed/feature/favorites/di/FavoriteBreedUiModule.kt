package com.tobioyelekan.dogbreed.feature.favorites.di

import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailRepository
import com.tobioyelekan.dogbreed.feature.favorites.GetFavoriteBreedsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteBreedUiModule {
    @Provides
    @Singleton
    fun provideGetFavoriteBreedsUseCase(
        repository: DogBreedDetailRepository
    ): GetFavoriteBreedsUseCase = GetFavoriteBreedsUseCase(repository)
}