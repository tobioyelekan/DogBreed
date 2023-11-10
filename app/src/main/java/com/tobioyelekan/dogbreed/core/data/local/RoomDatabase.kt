package com.tobioyelekan.dogbreed.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tobioyelekan.dogbreed.features.breeds.data.datasource.local.BreedDao
import com.tobioyelekan.dogbreed.features.breeds.data.datasource.local.model.BreedEntity

@Database(entities = [BreedEntity::class], version = 1, exportSchema = false)
abstract class DogBreedDatabase: RoomDatabase(){
    abstract fun breedDao(): BreedDao
}