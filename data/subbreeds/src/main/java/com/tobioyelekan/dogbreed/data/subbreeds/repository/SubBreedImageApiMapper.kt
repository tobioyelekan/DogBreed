package com.tobioyelekan.dogbreed.data.subbreeds.repository

import com.tobioyelekan.dogbreed.core.model.SubBreedImage
import com.tobioyelekan.dogbreed.core.network.model.SubBreedImageApiModel

fun SubBreedImageApiModel.toDomain(): List<SubBreedImage> {
    return this.images.map { SubBreedImage(it) }
}