package com.tobioyelekan.dogbreed.core.database.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.tobioyelekan.dogbreed.core.database.DATABASE_FILE_NAME
import com.tobioyelekan.dogbreed.core.database.DogBreedDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<RoomDatabase.Builder<DogBreedDatabase>> {
        val dbFile = java.io.File(System.getProperty("java.io.tmpdir"), DATABASE_FILE_NAME)
        Room.databaseBuilder<DogBreedDatabase>(
            name = dbFile.absolutePath,
        )
    }
}
