package com.tobioyelekan.dogbreed.features.breeds.data.datasource.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tobioyelekan.dogbreed.features.breeds.data.datasource.api.model.AllBreed

@JsonClass(generateAdapter = true)
data class GetAllBreedResponse(
    @Json(name = "message") val message: AllBreed
)