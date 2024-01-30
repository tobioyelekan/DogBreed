package com.tobioyelekan.dogbreed.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity

@Database(entities = [DogBreedEntity::class], version = 1, exportSchema = false)
abstract class DogBreedDatabase: RoomDatabase(){
    abstract fun breedDao(): DogBreedDao
}