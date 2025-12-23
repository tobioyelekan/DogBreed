package com.tobioyelekan.dogbreed.feature.allbreeds.mapper

import com.tobioyelekan.dogbreed.core.database.entity.DogBreedEntity

fun Map.Entry<String, List<String>>.toEntity(
    imageUrl: String
): DogBreedEntity {
    return DogBreedEntity(
        name = this.key,
        imageUrl = imageUrl,
        subBreeds =this.value,
        isFavorite = false
    )
}