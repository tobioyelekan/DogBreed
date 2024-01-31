package com.tobioyelekan.dogbreed.data.breeddetails.repository

import android.database.sqlite.SQLiteException
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import com.tobioyelekan.dogbreed.core.model.DogBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogBreedDetailsRepositoryImpl @Inject constructor(
    private val dogBreedDao: DogBreedDao
) : DogBreedDetailRepository {
    override fun getBreedDetails(breedName: String): Flow<Result<DogBreed>> {
        return try {
            val breed = dogBreedDao.getBreed(breedName)
            breed.map { Result.Success(it.toDomainModel()) }
        } catch (e: SQLiteException) {
            flowOf(Result.Failure("Error occurred, please try again"))
        }
    }

    override suspend fun addFavoriteBreed(name: String): Result<Unit> {
        return try {
            dogBreedDao.updateBreed(name, true)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Failure("something went wrong, please try again")
        }
    }

    override suspend fun removeFavoriteBreed(name: String): Result<Unit> {
        return try {
            dogBreedDao.updateBreed(name, false)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Failure("something went wrong, please try again")
        }
    }

}
