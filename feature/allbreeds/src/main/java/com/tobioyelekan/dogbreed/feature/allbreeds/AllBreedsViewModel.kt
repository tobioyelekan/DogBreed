package com.tobioyelekan.dogbreed.feature.allbreeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobioyelekan.dogbreed.domain.allbreeds.GetDogBreedListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.tobioyelekan.dogbreed.core.common.result.Result

@HiltViewModel
class AllBreedsViewModel @Inject constructor(
    private val getDogBreedListUseCase: GetDogBreedListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AllBreedsUiState>(AllBreedsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getDogBreeds()
    }

    private fun getDogBreeds() {
        viewModelScope.launch {
            when (val result = getDogBreedListUseCase()) {
                is Result.Success -> _uiState.update { AllBreedsUiState.Success(result.value) }
                is Result.Failure -> _uiState.update { AllBreedsUiState.Error(result.errorMessage) }
            }
        }
    }
}