package com.tobioyelekan.dogbreed.core.network

import com.tobioyelekan.dogbreed.core.network.api.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.model.BreedImageApiModel
import com.tobioyelekan.dogbreed.core.network.model.DogBreedsApiModel
import com.tobioyelekan.dogbreed.core.network.model.SubBreedImageApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorDogBreedApiService(
    private val client: HttpClient
) : DogBreedApiService {

    override suspend fun getAllDogBreeds(): Result<DogBreedsApiModel> =
        runCatching {
            client.get("breeds/list/all").body()
        }

    override suspend fun getBreedRandomImage(
        breedName: String
    ): Result<BreedImageApiModel> = runCatching {
        client.get("breed/$breedName/images/random").body()
    }

    override suspend fun getSubBreedImages(
        breedName: String,
        subBreedName: String
    ): Result<SubBreedImageApiModel> = runCatching {
        client.get("breed/$breedName/$subBreedName/images").body()
    }
}
