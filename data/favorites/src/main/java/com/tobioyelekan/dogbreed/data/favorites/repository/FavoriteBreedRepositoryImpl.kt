package com.tobioyelekan.dogbreed.data.favorites.repository

import android.database.sqlite.SQLiteException
import com.tobioyelekan.dogbreed.core.database.dao.DogBreedDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.database.entity.toDomainModel
import javax.inject.Inject

class FavoriteBreedRepositoryImpl @Inject constructor(
    private val dogBreedDao: DogBreedDao
) : FavoriteBreedRepository {
    override fun getFavoriteBreeds(): Flow<Result<List<com.tobioyelekan.dogbreed.core.model.DogBreed>>> {
        return try {
            val result = dogBreedDao.getFavoriteBreeds().map { list ->
                list.map {
                    it.toDomainModel()
                }
            }
            result.map { Result.Success(it) }
        } catch (e: SQLiteException) {
            flowOf(Result.Failure("Error occurred, please try again"))
        }
    }
}