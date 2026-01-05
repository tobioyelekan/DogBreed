package com.tobioyelekan.dogbreed.core.database.di

import androidx.room.RoomDatabase
import com.tobioyelekan.dogbreed.core.database.DogBreedDatabase
import com.tobioyelekan.dogbreed.core.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single <RoomDatabase.Builder<DogBreedDatabase>> {
        getDatabaseBuilder(get())
    }
}