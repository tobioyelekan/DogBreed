package com.tobioyelekan.dogbreed.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<DogBreedDatabase> {
    val dbFile = context.applicationContext
        .getDatabasePath(DATABASE_FILE_NAME)

    return Room.databaseBuilder(
        context.applicationContext,
        dbFile.absolutePath
    )
}