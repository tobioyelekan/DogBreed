package com.tobioyelekan.dogbreed.core.model

data class DogBreed(
    val name: String,
    val imageUrl: String,
    val subBreeds: List<String>,
    val isFavorite: Boolean
)