package com.tobioyelekan.dogbreed.core.network

import com.tobioyelekan.dogbreed.core.network.api.DogBreedApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = httpClient()

    @Provides
    @Singleton
    fun provideDogBreedApiService(httpClient: HttpClient): DogBreedApiService {
        return KtorDogBreedApiService(httpClient)
    }
}