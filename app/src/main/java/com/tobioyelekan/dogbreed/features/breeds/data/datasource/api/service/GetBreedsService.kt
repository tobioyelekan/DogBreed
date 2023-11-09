package com.tobioyelekan.dogbreed.features.breeds.data.datasource.api.service

import com.tobioyelekan.dogbreed.core.data.remote.ApiResult
import com.tobioyelekan.dogbreed.features.breeds.data.datasource.api.response.GetAllBreedResponse
import retrofit2.http.GET

interface GetBreedsService {
    @GET("breeds/list/all")
    suspend fun getAllDogBreeds(): ApiResult<GetAllBreedResponse>
}