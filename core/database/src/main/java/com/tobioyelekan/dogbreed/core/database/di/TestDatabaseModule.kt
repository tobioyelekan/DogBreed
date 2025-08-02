package com.tobioyelekan.dogbreed.core.database.di

import android.content.Context
import androidx.room.Room
import com.tobioyelekan.dogbreed.core.database.DogBreedDatabase
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {
    @Provides
    @Singleton
    fun provideInMemoryDatabase(@ApplicationContext context: Context): DogBreedDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            DogBreedDatabase::class.java
        )
        .build()
    }

    @Provides
    @Singleton
    fun provideTestBreedsDao(database: DogBreedDatabase): DogBreedDao {
        return database.breedDao()
    }
}