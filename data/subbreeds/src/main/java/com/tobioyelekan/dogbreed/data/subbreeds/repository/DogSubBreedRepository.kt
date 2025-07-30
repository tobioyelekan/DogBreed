package com.tobioyelekan.dogbreed.data.subbreeds.repository

import com.tobioyelekan.dogbreed.core.model.SubBreedImage

interface DogSubBreedRepository {
    suspend fun getSubBreeds(breedName: String, subBreedName: String): Result<List<SubBreedImage>>
}