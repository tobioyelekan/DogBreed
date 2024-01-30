package com.tobioyelekan.dogbreed.feature.allbreeds

import androidx.compose.runtime.Immutable
import com.tobioyelekan.dogbreed.core.model.DogBreed

@Immutable
sealed class AllBreedsUiState {
    object Loading : AllBreedsUiState()
    class Success(val dogBreeds: List<DogBreed>): AllBreedsUiState()
    class Error(val message: String) : AllBreedsUiState()
}

