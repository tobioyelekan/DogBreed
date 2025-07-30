package com.tobioyelekan.dogbreed.data.allbreeds.repository

import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.data.allbreeds.mapper.toEntity
import com.tobioyelekan.dogbreed.data.allbreeds.util.mergeEntities
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DogBreedsRepositoryImpl @Inject constructor(
    private val dogBreedDao: DogBreedDao,
    private val dogBreedService: DogBreedApiService
) : DogBreedsRepository {

    override suspend fun getAllBreeds(): Result<List<DogBreed>> {
        return runCatching {
            val dogBreedEntities = coroutineScope {
                val response = dogBreedService.getAllDogBreeds()
                response.breeds.map { breed ->
                    async {
                        val image = dogBreedService.getBreedRandomImage(breed.key)
                        breed.toEntity(image.imageUrl)
                    }
                }.awaitAll()
            }

            val cachedFavoritesBreeds = dogBreedDao.getAllBreeds().filter { it.isFavorite }

            val entities = if (cachedFavoritesBreeds.isEmpty())
                dogBreedEntities
            else
                mergeEntities(dogBreedEntities, cachedFavoritesBreeds)

            dogBreedDao.nukeTable()
            dogBreedDao.saveBreeds(entities)

            entities.map { it.toDomainModel() }
        }
            .recoverCatching {
                val breeds = dogBreedDao
                    .getAllBreeds()
                    .map { it.toDomainModel() }

                breeds.ifEmpty { throw it }
            }
    }
}
