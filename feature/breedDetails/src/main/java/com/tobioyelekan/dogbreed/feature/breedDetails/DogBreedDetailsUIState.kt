package com.tobioyelekan.dogbreed.feature.breedDetails

import androidx.compose.runtime.Immutable
import com.tobioyelekan.dogbreed.core.model.DogBreed

@Immutable
sealed class DogBreedDetailsUIState {
    object Loading : DogBreedDetailsUIState()
    class Success(val breedDetails: DogBreed) : DogBreedDetailsUIState()
    class Error(val message: String) : DogBreedDetailsUIState()
}