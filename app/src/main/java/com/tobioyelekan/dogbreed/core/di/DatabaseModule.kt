package com.tobioyelekan.dogbreed.core.di

import android.content.Context
import androidx.room.Room
import com.tobioyelekan.dogbreed.core.data.local.DogBreedDatabase
import com.tobioyelekan.dogbreed.features.breeds.data.datasource.local.model.BreedDao
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
    fun providesDatabase(@ApplicationContext context: Context): DogBreedDatabase {
        return Room.databaseBuilder(
            context,
            DogBreedDatabase::class.java,
            "DogBreedDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun providesBreedsDao(database: DogBreedDatabase): BreedDao {
        return database.breedDao()
    }
}