package com.tobioyelekan.dogbreed.data.subbreeds.repository

import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.model.SubBreedImage
import com.tobioyelekan.dogbreed.data.subbreeds.mapper.toDomain
import javax.inject.Inject

class DogSubBreedRepositoryImpl @Inject constructor(
    private val dogBreedService: DogBreedApiService
) : DogSubBreedRepository {
    override suspend fun getSubBreeds(
        breedName: String,
        subBreedName: String
    ): Result<List<SubBreedImage>> {
        return runCatching {
            dogBreedService.getSubBreedImages(
                breedName = breedName,
                subBreedName = subBreedName
            ).toDomain()
        }
    }
}