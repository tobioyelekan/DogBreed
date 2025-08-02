package com.tobioyelekan.dogbreed.core.network

import com.tobioyelekan.dogbreed.core.network.model.BreedImageApiModel
import com.tobioyelekan.dogbreed.core.network.model.DogBreedsApiModel
import com.tobioyelekan.dogbreed.core.network.model.SubBreedImageApiModel

class FakeDogBreedApiService : DogBreedApiService {
    companion object {
        var allBreedAPIErrorOccurred = false
        var subbreedAPIErrorOccurred = false
    }

    override suspend fun getAllDogBreeds(): DogBreedsApiModel {
        if (allBreedAPIErrorOccurred) throw Exception("Error occurred")

        return DogBreedsApiModel(
            breeds = mapOf(
                "australian" to listOf("cattle", "kelpie", "shepherd"),
                "affenpinscher" to emptyList(),
                "african" to emptyList(),
                "airedale" to emptyList(),
                "akita" to emptyList(),
                "appenzeller" to emptyList(),
                "basenji" to emptyList(),
            ),
        )
    }

    override suspend fun getBreedRandomImage(breedName: String): BreedImageApiModel {
        return BreedImageApiModel(
            imageUrl = "https://images.dog.ceo/breeds/" +
                    "$breedName/fake_${breedName}_${System.currentTimeMillis()}.jpg",
        )
    }

    override suspend fun getSubBreedImages(
        breedName: String,
        subBreedName: String
    ): SubBreedImageApiModel {
        if (subbreedAPIErrorOccurred) throw Exception("Error occurred")

        val fakeImages = (1..5).map {
            "https://images.dog.ceo/breeds/$breedName-$subBreedName/fake_${subBreedName}_$it.jpg"
        }

        return SubBreedImageApiModel(images = fakeImages)
    }
}