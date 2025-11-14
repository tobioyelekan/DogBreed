package com.tobioyelekan.dogbreed.allbreeds.mapper

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
