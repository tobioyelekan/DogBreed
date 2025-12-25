package com.tobioyelekan.dogbreed.feature.subbreeds

import androidx.compose.runtime.Immutable
import com.tobioyelekan.dogbreed.core.model.SubBreedImage

@Immutable
sealed class SubBreedUIState {
    object Loading : SubBreedUIState()
    class Success(val images: List<SubBreedImage>) : SubBreedUIState()
    class Error(val message: String) : SubBreedUIState()
}