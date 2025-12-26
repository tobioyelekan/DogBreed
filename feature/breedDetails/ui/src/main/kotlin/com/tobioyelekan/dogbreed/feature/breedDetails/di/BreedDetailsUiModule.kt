package com.tobioyelekan.dogbreed.feature.breedDetails.di

import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailRepository
import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.AddFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.DeleteFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.feature.breedDetails.usecase.GetBreedDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BreedDetailsUiModule {
    @Provides
    @Singleton
    fun provideAddFavoriteBreedUseCase(
        repository: DogBreedDetailRepository
    ): AddFavoriteBreedUseCase = AddFavoriteBreedUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteFavoriteBreedUseCase(
        repository: DogBreedDetailRepository
    ): DeleteFavoriteBreedUseCase = DeleteFavoriteBreedUseCase(repository)

    @Provides
    @Singleton
    fun provideGetBreedDetailsUseCase(
        repository: DogBreedDetailRepository
    ): GetBreedDetailsUseCase = GetBreedDetailsUseCase(repository)
}