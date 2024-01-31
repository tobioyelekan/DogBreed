package com.tobioyelekan.dogbreed.data.subbreeds.repository

import com.tobioyelekan.dogbreed.core.network.DogBreedApiService
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.model.SubBreedImage
import com.tobioyelekan.dogbreed.core.network.adapter.ApiResult
import javax.inject.Inject

class DogSubBreedRepositoryImpl @Inject constructor(
    private val dogBreedService: DogBreedApiService
) : DogSubBreedRepository {
    override suspend fun getSubBreeds(
        breedName: String,
        subBreedName: String
    ): Result<List<SubBreedImage>> {
        return when (
            val response = dogBreedService
                .getSubBreedImages(
                    breedName = breedName,
                    subBreedName = subBreedName
                )
        ) {
            is ApiResult.Success -> {
                val data = response.data.toDomain()
                Result.Success(value = data)
            }

            is ApiResult.Error -> {
                Result.Failure("Something went wrong")
            }

            is ApiResult.Exception -> {
                Result.Failure(response.throwable.message ?: "Something went wrong")
            }
        }
    }
//    kotlinx.serialization.json.internal.JsonDecodingException: Unexpected JSON token at offset 353: Encountered an unknown key 'status' at path: $.message

}