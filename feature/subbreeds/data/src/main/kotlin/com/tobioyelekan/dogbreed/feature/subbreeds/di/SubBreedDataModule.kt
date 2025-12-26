package com.tobioyelekan.dogbreed.feature.subbreeds.di

import com.tobioyelekan.dogbreed.feature.subbreeds.repository.DogSubBreedRepository
import com.tobioyelekan.dogbreed.feature.subbreeds.repository.DogSubBreedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SubBreedDataModule {
    @Binds
    fun bindsSubBreedsRepository(
        impl: DogSubBreedRepositoryImpl
    ): DogSubBreedRepository
}