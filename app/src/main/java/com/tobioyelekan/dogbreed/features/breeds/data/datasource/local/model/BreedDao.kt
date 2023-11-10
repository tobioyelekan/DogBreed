package com.tobioyelekan.dogbreed.features.breeds.data.datasource.local.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BreedDao {
    @Insert
    suspend fun saveBreeds(breeds: List<BreedEntity>)

    @Query("SELECT * FROM breed_table")
    suspend fun getAllBreeds(): List<BreedEntity>
}