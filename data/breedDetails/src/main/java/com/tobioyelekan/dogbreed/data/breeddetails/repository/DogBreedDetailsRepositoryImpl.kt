package com.tobioyelekan.dogbreed.data.breeddetails.repository

import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.model.DogBreed
import kotlinx.coroutines.flow.Flow
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogBreedDetailsRepositoryImpl @Inject constructor(
    private val dogBreedDao: DogBreedDao
) : DogBreedDetailRepository {
    override fun getBreedDetails(breedName: String): Flow<Result<DogBreed>> {
        return dogBreedDao.getBreed(breedName)
            .map { runCatching { it.toDomainModel() } }
            .catch { emit(Result.failure(it)) }
    }

    override fun getFavoriteBreeds(): Flow<Result<List<DogBreed>>> {
        return dogBreedDao.getFavoriteBreeds()
            .map { entities->
                runCatching { entities.map { it.toDomainModel() } }
            }
            .catch { emit(Result.failure(it)) }
    }

    override suspend fun addFavoriteBreed(name: String): Result<Unit> {
        return runCatching {
            dogBreedDao.updateBreed(name, true)
        }
    }

    override suspend fun removeFavoriteBreed(name: String): Result<Unit> {
        return runCatching {
            dogBreedDao.updateBreed(name, false)
        }
    }
}
