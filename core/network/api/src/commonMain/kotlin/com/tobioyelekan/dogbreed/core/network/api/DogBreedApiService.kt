package com.tobioyelekan.dogbreed.core.network.api

import com.tobioyelekan.dogbreed.core.network.model.BreedImageApiModel
import com.tobioyelekan.dogbreed.core.network.model.DogBreedsApiModel
import com.tobioyelekan.dogbreed.core.network.model.SubBreedImageApiModel

interface DogBreedApiService {
    suspend fun getAllDogBreeds(): Result<DogBreedsApiModel>
    suspend fun getBreedRandomImage(breedName: String): Result<BreedImageApiModel>
    suspend fun getSubBreedImages(
        breedName: String,
        subBreedName: String
    ): Result<SubBreedImageApiModel>
}