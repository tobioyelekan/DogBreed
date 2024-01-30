package com.tobioyelekan.dogbreed.domain.allbreeds

import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.data.allbreeds.repository.DogBreedsRepository
import javax.inject.Inject

class GetDogBreedListUseCase @Inject constructor(
    private val repository: DogBreedsRepository
) {
    suspend operator fun invoke(): Result<List<DogBreed>>{
        return repository.getAllBreeds()
    }
}