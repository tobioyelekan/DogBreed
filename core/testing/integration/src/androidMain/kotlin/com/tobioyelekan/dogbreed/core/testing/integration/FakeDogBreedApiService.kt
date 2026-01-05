package com.tobioyelekan.dogbreed.core.testing.integration

import com.tobioyelekan.dogbreed.core.network.api.DogBreedApiService
import com.tobioyelekan.dogbreed.core.network.model.BreedImageApiModel
import com.tobioyelekan.dogbreed.core.network.model.DogBreedsApiModel
import com.tobioyelekan.dogbreed.core.network.model.SubBreedImageApiModel

class FakeDogBreedApiService : DogBreedApiService {
    companion object {
        var allBreedAPIErrorOccurred = false
        var subbreedAPIErrorOccurred = false
    }

    override suspend fun getAllDogBreeds(): Result<DogBreedsApiModel> {
        if (allBreedAPIErrorOccurred) return Result.failure(Exception("Error occurred"))

        return Result.success(
            DogBreedsApiModel(
                mapOf(
                    "australian" to listOf("cattle", "kelpie", "shepherd"),
                    "affenpinscher" to emptyList(),
                    "african" to emptyList(),
                    "airedale" to emptyList(),
                    "akita" to emptyList(),
                    "appenzeller" to emptyList(),
                    "basenji" to emptyList(),
                )
            )
        )
    }

    override suspend fun getBreedRandomImage(breedName: String): Result<BreedImageApiModel> {
        return Result.success(
            BreedImageApiModel(
                imageUrl = "https://images.dog.ceo/breeds/" +
                        "$breedName/fake_${breedName}.jpg",
            )
        )
    }

    override suspend fun getSubBreedImages(
        breedName: String,
        subBreedName: String
    ): Result<SubBreedImageApiModel> {
        if (subbreedAPIErrorOccurred) return Result.failure(Exception("Error occurred"))

        val fakeImages = (1..5).map {
            "https://images.dog.ceo/breeds/$breedName-$subBreedName/fake_${subBreedName}_$it.jpg"
        }

        return Result.success(SubBreedImageApiModel(fakeImages))
    }
}