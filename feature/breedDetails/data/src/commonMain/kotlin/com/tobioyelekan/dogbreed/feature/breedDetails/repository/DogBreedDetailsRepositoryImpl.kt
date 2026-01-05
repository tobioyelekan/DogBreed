package com.tobioyelekan.dogbreed.feature.breedDetails.repository

import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.model.DogBreed
import kotlinx.coroutines.flow.Flow
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DogBreedDetailsRepositoryImpl(
    private val dogBreedDao: DogBreedDao,
    private val ioDispatcher: CoroutineDispatcher
) : DogBreedDetailRepository {
    override fun getBreedDetails(breedName: String): Flow<Result<DogBreed>> {
        return dogBreedDao.getBreed(breedName)
            .map { runCatching { it.toDomainModel() } }
            .catch { emit(Result.failure(it)) }
    }

    override fun getFavoriteBreeds(): Flow<Result<List<DogBreed>>> {
        return dogBreedDao.getFavoriteBreeds()
            .map { entities ->
                runCatching { entities.map { it.toDomainModel() } }
            }
            .catch { emit(Result.failure(it)) }
    }

    override suspend fun addFavoriteBreed(name: String) = withContext(ioDispatcher) {
        runCatching {
            dogBreedDao.updateBreed(name, true)
        }
    }

    override suspend fun removeFavoriteBreed(name: String) = withContext(ioDispatcher) {
        runCatching {
            dogBreedDao.updateBreed(name, false)
        }
    }
}
