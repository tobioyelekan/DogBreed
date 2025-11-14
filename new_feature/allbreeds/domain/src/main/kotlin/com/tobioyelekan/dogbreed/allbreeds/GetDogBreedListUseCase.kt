package com.tobioyelekan.dogbreed.allbreeds

import com.tobioyelekan.dogbreed.core.model.DogBreed
import javax.inject.Inject

class GetDogBreedListUseCase @Inject constructor(
    private val repository: DogBreedsRepository
) {
    suspend operator fun invoke(): Result<List<DogBreed>>{
        return repository.getAllBreeds()
    }
}
