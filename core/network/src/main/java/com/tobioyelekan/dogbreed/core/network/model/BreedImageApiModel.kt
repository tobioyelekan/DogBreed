package com.tobioyelekan.dogbreed.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedImageApiModel(
    @SerialName("message") val imageUrl: String,
)