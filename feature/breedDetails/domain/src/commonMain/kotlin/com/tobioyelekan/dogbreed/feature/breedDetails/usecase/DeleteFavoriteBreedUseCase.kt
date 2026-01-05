package com.tobioyelekan.dogbreed.feature.breedDetails.usecase

import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailRepository

class DeleteFavoriteBreedUseCase(
    private val dogBreedDetailRepository: DogBreedDetailRepository
) {
    suspend operator fun invoke(breedName: String): Result<Unit> {
        return dogBreedDetailRepository.removeFavoriteBreed(breedName)
    }
}