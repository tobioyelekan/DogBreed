package com.tobioyelekan.dogbreed.database.gateway

interface LocalDatabase {
    fun getAllBreeds(): List<DogBreedEntity>
    suspend fun saveBreeds(breeds: List<DogBreedEntity>)
    fun getBreed(name: String): Flow<DogBreedEntity>
    fun getFavoriteBreeds(): Flow<List<DogBreedEntity>>
}
