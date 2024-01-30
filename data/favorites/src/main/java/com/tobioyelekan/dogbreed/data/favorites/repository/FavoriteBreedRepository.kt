package com.tobioyelekan.dogbreed.data.favorites.repository

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.model.DogBreed
import kotlinx.coroutines.flow.Flow

interface FavoriteBreedRepository {
    fun getFavoriteBreeds(): Flow<Result<List<DogBreed>>>
}