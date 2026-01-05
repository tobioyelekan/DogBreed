package com.tobioyelekan.dogbreed.core.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity

@Database(entities = [DogBreedEntity::class], version = 1)
@ConstructedBy(DogBreedDatabaseConstructor::class)
abstract class DogBreedDatabase: RoomDatabase(){
    abstract fun breedDao(): DogBreedDao
}

@Suppress("KotlinNoActualForExpect")
expect object DogBreedDatabaseConstructor: RoomDatabaseConstructor<DogBreedDatabase> {
    override fun initialize(): DogBreedDatabase
}