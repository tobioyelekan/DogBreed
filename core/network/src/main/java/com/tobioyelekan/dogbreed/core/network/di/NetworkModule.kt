package com.tobioyelekan.dogbreed.core.network.di

import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.adapter.ApiResultAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import com.tobioyelekan.dogbreed.core.network.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

private const val CONNECT_TIMEOUT = 10L

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .addCallAdapterFactory(ApiResultAdapterFactory())
            .build()
    }

    @Provides
    fun provideDogBreedService(retrofit: Retrofit): DogBreedApiService {
        return retrofit.create(DogBreedApiService::class.java)
    }
}