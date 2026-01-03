package com.tobioyelekan.dogbreed.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class FavoriteBreedViewModel(
    getFavoriteBreedsUseCase: GetFavoriteBreedsUseCase
) : ViewModel() {

    val uiState: StateFlow<FavoriteBreedUIState> =
        getFavoriteBreedsUseCase()
            .map { result ->
                result.fold(
                    onSuccess = { data ->
                        FavoriteBreedUIState.Success(data)
                    },
                    onFailure = {
                        FavoriteBreedUIState.Error("something went wrong")
                    }
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FavoriteBreedUIState.Loading
            )
}