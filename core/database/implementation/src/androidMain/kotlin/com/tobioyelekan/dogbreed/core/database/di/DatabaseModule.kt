package com.tobioyelekan.dogbreed.core.database.di

import android.content.Context
import com.tobioyelekan.dogbreed.core.database.DogBreedDatabase
import com.tobioyelekan.dogbreed.core.database.createDatabase
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.getDatabaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): DogBreedDatabase {
        return createDatabase(getDatabaseBuilder(context))
    }

    @Provides
    fun provideDogBreedDao(
        db: DogBreedDatabase
    ): DogBreedDao = db.breedDao()
}