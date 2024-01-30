package com.tobioyelekan.dogbreed.data.subbreeds.di

import com.tobioyelekan.dogbreed.data.subbreeds.repository.DogSubBreedRepository
import com.tobioyelekan.dogbreed.data.subbreeds.repository.DogSubBreedRepositoryImpl
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