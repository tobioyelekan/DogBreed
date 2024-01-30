package com.tobioyelekan.dogbreed.domain.breedDetails

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailRepository
import javax.inject.Inject

class DeleteFavoriteBreedUsecase @Inject constructor(
    private val dogBreedDetailRepository: DogBreedDetailRepository
) {
    suspend operator fun invoke(breedName: String): Result<Unit> {
        return dogBreedDetailRepository.removeFavoriteBreed(breedName)
    }
}