package com.tobioyelekan.dogbreed.feature.subbreeds.repository

import com.tobioyelekan.dogbreed.core.model.SubBreedImage

interface DogSubBreedRepository {
    suspend fun getSubBreeds(breedName: String, subBreedName: String): Result<List<SubBreedImage>>
}