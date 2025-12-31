package com.tobioyelekan.dogbreed.feature.breedDetails.usecase

import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailRepository

class AddFavoriteBreedUseCase(
    private val dogBreedDetailRepository: DogBreedDetailRepository
) {
    suspend operator fun invoke(breedName: String): Result<Unit> {
        return dogBreedDetailRepository.addFavoriteBreed(breedName)
    }
}