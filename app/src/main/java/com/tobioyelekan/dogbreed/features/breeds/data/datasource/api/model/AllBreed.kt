package com.tobioyelekan.dogbreed.features.breeds.data.datasource.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllBreed(
    val breed: Map<String, List<String>>
)