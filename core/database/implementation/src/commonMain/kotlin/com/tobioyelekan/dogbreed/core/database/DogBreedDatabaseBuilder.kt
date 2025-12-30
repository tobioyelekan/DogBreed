package com.tobioyelekan.dogbreed.core.database

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

const val DATABASE_FILE_NAME = "dog_breed.db"

fun createDatabase(
    builder: RoomDatabase.Builder<DogBreedDatabase>
): DogBreedDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
