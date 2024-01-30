package com.tobioyelekan.dogbreed.feature.favorites//package com.tobioyelekan.dogbreed.features.favorites.presentation

import androidx.compose.runtime.Immutable
import com.tobioyelekan.dogbreed.core.model.DogBreed

@Immutable
sealed class FavoriteBreedUIState {
    object Loading : FavoriteBreedUIState()
    object Empty : FavoriteBreedUIState()
    class Success(val favoriteBreeds: List<DogBreed>) : FavoriteBreedUIState()
    class Error(val message: String) : FavoriteBreedUIState()
}