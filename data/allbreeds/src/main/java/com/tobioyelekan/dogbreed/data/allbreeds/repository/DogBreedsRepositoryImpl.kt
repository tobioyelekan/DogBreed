package com.tobioyelekan.dogbreed.data.allbreeds.repository

import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity
import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.adapter.ApiResult
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import javax.inject.Inject

class DogBreedsRepositoryImpl @Inject constructor(
    private val dogBreedDao: DogBreedDao,
    private val dogBreedService: DogBreedApiService
) : DogBreedsRepository {

    override suspend fun getAllBreeds(): Result<List<com.tobioyelekan.dogbreed.core.model.DogBreed>> {
        return when (val response = dogBreedService.getAllDogBreeds()) {
            is ApiResult.Success -> {
                val dogBreedEntities = mutableListOf<DogBreedEntity>()

                response.data.breeds.map { breed ->
                    val breedImageResponse = dogBreedService.getBreedRandomImage(breed.key)
                    if (breedImageResponse is ApiResult.Success) {
                        dogBreedEntities.add(
                            DogBreedEntity(
                                name = breed.key,
                                imageUrl = breedImageResponse.data.imageUrl,
                                subBreeds = breed.value,
                                isFavorite = false
                            )
                        )
                    } else {
                        return Result.Failure("Something went wrong")
                    }
                }

                //start 
                //isFavorite doesn't come from the backend, hence when a refresh to the server is called, isFavorite field in the db is overwritten to false,
                //to avoid that we first get the locally stored breeds with `true` value
                //and then use that to update the fields gotten from the internet where their name (unique identity) matches.
                val cachedFavoritesBreeds = dogBreedDao.getAllBreeds().filter { it.isFavorite }

                if (cachedFavoritesBreeds.isNotEmpty()) {
                    cachedFavoritesBreeds.forEach { breed ->
                        dogBreedEntities.find { it.name == breed.name }?.let {
                            it.isFavorite = true
                        }
                    }
                    dogBreedDao.saveBreeds(dogBreedEntities)
                } else {
                    dogBreedDao.saveBreeds(dogBreedEntities)
                }
                //end

                Result.Success(dogBreedDao.getAllBreeds().map { it.toDomainModel() })
            }

            is ApiResult.Error -> {
                Result.Failure("Something went wrong")
            }

            is ApiResult.Exception -> {
                val breeds = dogBreedDao
                    .getAllBreeds()
                    .map { it.toDomainModel() }

                if (breeds.isNotEmpty()) {
                    return Result.Success(breeds)
                }

                Result.Failure(response.throwable.message ?: "Something went wrong")
            }
        }
    }
}