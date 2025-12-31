package com.tobioyelekan.dogbreed.feature.subbreeds.repository

import com.tobioyelekan.dogbreed.core.model.SubBreedImage
import com.tobioyelekan.dogbreed.core.network.api.DogBreedApiService
import com.tobioyelekan.dogbreed.feature.subbreeds.mapper.toDomain

class DogSubBreedRepositoryImpl(
    private val dogBreedService: DogBreedApiService
) : DogSubBreedRepository {
    override suspend fun getSubBreeds(
        breedName: String,
        subBreedName: String
    ): Result<List<SubBreedImage>> =
        dogBreedService.getSubBreedImages(
            breedName = breedName,
            subBreedName = subBreedName
        )
            .mapCatching { it.toDomain() }
}