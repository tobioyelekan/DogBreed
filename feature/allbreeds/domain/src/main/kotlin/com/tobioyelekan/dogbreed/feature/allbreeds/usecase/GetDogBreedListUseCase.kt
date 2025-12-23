package com.tobioyelekan.dogbreed.feature.allbreeds.usecase

import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.feature.allbreeds.repository.DogBreedsRepository

class GetDogBreedListUseCase(
    private val repository: DogBreedsRepository
) {
    suspend operator fun invoke(): Result<List<DogBreed>>{
        return repository.getAllBreeds()
    }
}