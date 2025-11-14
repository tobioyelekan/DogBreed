package com.tobioyelekan.dogbreed.allbreeds

import com.tobioyelekan.dogbreed.core.model.DogBreed

interface DogBreedsRepository {
    suspend fun getAllBreeds(): Result<List<DogBreed>>
}
