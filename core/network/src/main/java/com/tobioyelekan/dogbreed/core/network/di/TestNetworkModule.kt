package com.tobioyelekan.dogbreed.core.network.di

import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.FakeDogBreedApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object TestNetworkModule {
    @Provides
    @Singleton
    fun provideFakeDogBreedApiService(): DogBreedApiService {
        return FakeDogBreedApiService()
    }
}