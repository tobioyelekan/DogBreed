package com.tobioyelekan.dogbreed.feature.breedDetails.usecase

import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailRepository
import kotlinx.coroutines.flow.Flow

class GetBreedDetailsUseCase(
    private val repository: DogBreedDetailRepository
) {
    operator fun invoke(breedName: String): Flow<Result<DogBreed>> {
        return repository.getBreedDetails(breedName)
    }
}