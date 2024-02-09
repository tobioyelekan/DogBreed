package com.tobioyelekan.dogbreed.testing.data

import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.core.model.SubBreedImage

object TestData {
    val dogBreedApiResponseTestData = mapOf(
        "dog1" to listOf("dog1a", "dog1b"),
        "dog2" to emptyList(),
        "dog3" to listOf("dog3a"),
    )

    val dogBreeds = listOf(
        DogBreed("dog1", "imageUrl1", listOf("doga", "dogb"), false),
        DogBreed("dog2", "imageUrl2", emptyList(), false),
        DogBreed("dog3", "imageUrl3", listOf("subbreed"), true),
        DogBreed("dog4", "imageUrl4", listOf("dog1", "dog2", "dog3"), false),
    )

    val subBreedImages = listOf(
        SubBreedImage("imageUrl1"),
        SubBreedImage("imageUrl2"),
        SubBreedImage("imageUrl3"),
        SubBreedImage("imageUrl4")
    )
}