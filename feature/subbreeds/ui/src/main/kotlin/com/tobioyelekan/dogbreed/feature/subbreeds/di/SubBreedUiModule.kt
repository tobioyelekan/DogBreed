package com.tobioyelekan.dogbreed.feature.subbreeds.di

import com.tobioyelekan.dogbreed.feature.subbreeds.repository.DogSubBreedRepository
import com.tobioyelekan.dogbreed.feature.subbreeds.usecase.GetSubBreedImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SubBreedUiModule {
    @Provides
    @Singleton
    fun provideGetSubBreedImageUseCase(
        repository: DogSubBreedRepository
    ): GetSubBreedImageUseCase = GetSubBreedImageUseCase(repository)
}