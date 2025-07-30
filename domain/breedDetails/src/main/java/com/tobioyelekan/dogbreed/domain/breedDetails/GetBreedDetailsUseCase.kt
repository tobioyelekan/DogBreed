package com.tobioyelekan.dogbreed.domain.breedDetails

import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBreedDetailsUseCase @Inject constructor(
    private val repository: DogBreedDetailRepository
) {
    operator fun invoke(breedName: String): Flow<Result<DogBreed>> {
        return repository.getBreedDetails(breedName)
    }
}