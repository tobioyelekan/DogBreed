package com.tobioyelekan.dogbreed.core.network

import com.tobioyelekan.dogbreed.core.network.adapter.ApiResult
import com.tobioyelekan.dogbreed.core.network.model.BreedImageApiModel
import com.tobioyelekan.dogbreed.core.network.model.DogBreedsApiModel
import com.tobioyelekan.dogbreed.core.network.model.SubBreedImageApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedApiService {
    @GET("breeds/list/all")
    suspend fun getAllDogBreeds(): ApiResult<DogBreedsApiModel>

    @GET("breed/{breedName}/images/random")
    suspend fun getBreedRandomImage(
        @Path("breedName") breedName: String
    ): ApiResult<BreedImageApiModel>

    @GET("breed/{breedName}/{subBreedName}/images")
    suspend fun getSubBreedImages(
        @Path("breedName") breedName: String,
        @Path("subBreedName") subBreedName: String
    ): ApiResult<SubBreedImageApiModel>
}