package com.tobioyelekan.dogbreed.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DogBreedsApiModel(
    @SerialName("message") val breeds: Map<String, List<String>>
)