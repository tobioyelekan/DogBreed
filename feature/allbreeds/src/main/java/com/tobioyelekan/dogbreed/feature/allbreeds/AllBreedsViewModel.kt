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
import kotlinx.coroutines.CoroutineDispatcher

@HiltViewModel
class AllBreedsViewModel @Inject constructor(
    private val getDogBreedListUseCase: GetDogBreedListUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<AllBreedsUiState>(AllBreedsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getDogBreeds()
    }

    private fun getDogBreeds() {
        viewModelScope.launch(ioDispatcher) {
            getDogBreedListUseCase()
                .onSuccess { value ->
                    _uiState.update { AllBreedsUiState.Success(value) }
                }
                .onFailure {
                    _uiState.update { AllBreedsUiState.Error("something went wrong") }
                }
        }
    }
}