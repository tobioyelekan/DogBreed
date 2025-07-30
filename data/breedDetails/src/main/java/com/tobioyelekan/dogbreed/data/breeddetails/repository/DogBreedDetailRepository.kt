package com.tobioyelekan.dogbreed.data.breeddetails.repository

import com.tobioyelekan.dogbreed.core.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface DogBreedDetailRepository {
    fun getBreedDetails(breedName: String): Flow<Result<DogBreed>>
    fun getFavoriteBreeds(): Flow<Result<List<DogBreed>>>
    suspend fun addFavoriteBreed(name:String): Result<Unit>
    suspend fun removeFavoriteBreed(name:String): Result<Unit>
}