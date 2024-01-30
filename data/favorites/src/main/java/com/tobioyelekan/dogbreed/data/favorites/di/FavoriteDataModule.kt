package com.tobioyelekan.dogbreed.data.favorites.di

import com.tobioyelekan.dogbreed.data.favorites.repository.FavoriteBreedRepository
import com.tobioyelekan.dogbreed.data.favorites.repository.FavoriteBreedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoriteDataModule {
    @Binds
     fun bindsFavoriteBreedsRepository(
        impl: FavoriteBreedRepositoryImpl
    ): FavoriteBreedRepository
}