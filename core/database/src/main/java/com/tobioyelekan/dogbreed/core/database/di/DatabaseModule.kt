package com.tobioyelekan.dogbreed.core.database.di

import android.content.Context
import androidx.room.Room
import com.tobioyelekan.dogbreed.core.database.DogBreedDatabase
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
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
        )
            .build()
    }

    @Provides
    @Singleton
    fun providesBreedsDao(database: DogBreedDatabase): DogBreedDao {
        return database.breedDao()
    }
}