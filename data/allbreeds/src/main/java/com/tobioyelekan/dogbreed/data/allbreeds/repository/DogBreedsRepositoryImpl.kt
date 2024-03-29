package com.tobioyelekan.dogbreed.data.allbreeds.repository

import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity
import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.adapter.ApiResult
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.data.allbreeds.mapper.toEntity
import com.tobioyelekan.dogbreed.data.allbreeds.util.mergeEntities
import javax.inject.Inject

class DogBreedsRepositoryImpl @Inject constructor(
    private val dogBreedDao: DogBreedDao,
    private val dogBreedService: DogBreedApiService
) : DogBreedsRepository {

    override suspend fun getAllBreeds(): Result<List<DogBreed>> {
        return when (val response = dogBreedService.getAllDogBreeds()) {
            is ApiResult.Success -> {
                val dogBreedEntities = mutableListOf<DogBreedEntity>()

                response.data.breeds.map { breed ->
                    val breedImageResponse = dogBreedService.getBreedRandomImage(breed.key)
                    if (breedImageResponse is ApiResult.Success) {
                        dogBreedEntities.add(
                            breed.toEntity(breedImageResponse.data.imageUrl)
                        )
                    } else {
                        return Result.Failure("Something went wrong, please contact support and try again")
                    }
                }

                val cachedFavoritesBreeds = dogBreedDao.getAllBreeds().filter { it.isFavorite }

                val entities = if (cachedFavoritesBreeds.isEmpty())
                    dogBreedEntities
                else
                    mergeEntities(dogBreedEntities, cachedFavoritesBreeds)

                dogBreedDao.nukeTable()
                dogBreedDao.saveBreeds(entities)
                //end

                Result.Success(entities.map { it.toDomainModel() })
            }

            is ApiResult.Error -> {
                return Result.Failure("Something went wrong, please contact support and try again")
            }

            is ApiResult.Exception -> {
                val breeds = dogBreedDao
                    .getAllBreeds()
                    .map { it.toDomainModel() }

                if (breeds.isNotEmpty()) {
                    return Result.Success(breeds)
                }

                Result.Failure( "Please check your internet connection and try again")
            }
        }
    }
}