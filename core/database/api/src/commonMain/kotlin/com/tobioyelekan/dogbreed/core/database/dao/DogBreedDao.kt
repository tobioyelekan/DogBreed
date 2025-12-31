package com.tobioyelekan.dogbreed.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DogBreedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBreeds(breeds: List<DogBreedEntity>)

    @Query("SELECT * FROM breed_table")
    suspend fun getAllBreeds(): List<DogBreedEntity>

    @Query("SELECT * FROM breed_table where name=:name")
    fun getBreed(name: String): Flow<DogBreedEntity>

    @Query("SELECT * FROM breed_table where isFavorite=1")
    fun getFavoriteBreeds(): Flow<List<DogBreedEntity>>

    @Query("UPDATE breed_table SET isFavorite=:isFavorite WHERE name =:breedName")
    fun updateBreed(breedName: String, isFavorite: Boolean)

    @Query("DELETE FROM breed_table")
    fun nukeTable()
}