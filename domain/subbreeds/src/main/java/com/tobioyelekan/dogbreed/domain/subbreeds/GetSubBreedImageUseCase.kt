package com.tobioyelekan.dogbreed.domain.subbreeds

import com.tobioyelekan.dogbreed.core.model.SubBreedImage
import com.tobioyelekan.dogbreed.data.subbreeds.repository.DogSubBreedRepository
import javax.inject.Inject

class GetSubBreedImageUseCase @Inject constructor(
    private val repository: DogSubBreedRepository
) {
    suspend operator fun invoke(
        breedName: String,
        subBreedName: String
    ): Result<List<SubBreedImage>> {
        return repository.getSubBreeds(breedName, subBreedName)
    }
}