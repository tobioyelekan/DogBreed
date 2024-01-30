package com.tobioyelekan.dogbreed.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobioyelekan.dogbreed.domain.favorites.GetFavoriteBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import com.tobioyelekan.dogbreed.core.common.result.Result

@HiltViewModel
class FavoriteBreedViewModel @Inject constructor(
    getFavoriteBreedsUseCase: GetFavoriteBreedsUseCase
) : ViewModel() {

    val uiState: StateFlow<FavoriteBreedUIState> =
        getFavoriteBreedsUseCase().map { result ->
            when (result) {
                is Result.Success -> {
                    if (result.value.isEmpty()) return@map FavoriteBreedUIState.Empty
                    FavoriteBreedUIState.Success(result.value)
                }

                is Result.Failure -> FavoriteBreedUIState.Error(result.errorMessage)
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FavoriteBreedUIState.Loading
            )
}