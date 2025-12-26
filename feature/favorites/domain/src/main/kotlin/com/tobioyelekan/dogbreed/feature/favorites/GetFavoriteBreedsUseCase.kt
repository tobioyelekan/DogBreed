package com.tobioyelekan.dogbreed.feature.favorites

import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.feature.breedDetails.repository.DogBreedDetailRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteBreedsUseCase(
    private val repository: DogBreedDetailRepository
) {
    operator fun invoke(): Flow<Result<List<DogBreed>>> {
        return repository.getFavoriteBreeds()
    }
}