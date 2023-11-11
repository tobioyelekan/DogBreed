package com.tobioyelekan.dogbreed.features.breeds.data.datasource.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllBreedApiModel(
    val breed: Map<String, List<String>>
)