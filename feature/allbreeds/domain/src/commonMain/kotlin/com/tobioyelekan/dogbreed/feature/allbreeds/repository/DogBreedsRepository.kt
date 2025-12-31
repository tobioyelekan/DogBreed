package com.tobioyelekan.dogbreed.feature.allbreeds.repository

import com.tobioyelekan.dogbreed.core.model.DogBreed

interface DogBreedsRepository {
    suspend fun getAllBreeds(): Result<List<DogBreed>>
}