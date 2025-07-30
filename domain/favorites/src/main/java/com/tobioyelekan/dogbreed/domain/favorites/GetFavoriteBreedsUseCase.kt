package com.tobioyelekan.dogbreed.domain.favorites

import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.data.breeddetails.repository.DogBreedDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBreedsUseCase @Inject constructor(
    private val repository: DogBreedDetailRepository
) {
    operator fun invoke(): Flow<Result<List<DogBreed>>> {
        return repository.getFavoriteBreeds()
    }
}