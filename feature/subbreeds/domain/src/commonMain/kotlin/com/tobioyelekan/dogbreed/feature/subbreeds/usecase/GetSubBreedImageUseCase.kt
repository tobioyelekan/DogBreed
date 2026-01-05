package com.tobioyelekan.dogbreed.feature.subbreeds.usecase

import com.tobioyelekan.dogbreed.core.model.SubBreedImage
import com.tobioyelekan.dogbreed.feature.subbreeds.repository.DogSubBreedRepository

class GetSubBreedImageUseCase(
    private val repository: DogSubBreedRepository
) {
    suspend operator fun invoke(
        breedName: String,
        subBreedName: String
    ): Result<List<SubBreedImage>> {
        return repository.getSubBreeds(breedName, subBreedName)
    }
}