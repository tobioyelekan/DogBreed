package com.tobioyelekan.dogbreed.feature.subbreeds

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.tobioyelekan.dogbreed.core.common.toTitleCase
import com.tobioyelekan.dogbreed.feature.subbreeds.navigation.breedNameArgs
import com.tobioyelekan.dogbreed.feature.subbreeds.navigation.subBreedNameArgs
import com.tobioyelekan.dogbreed.feature.subbreeds.usecase.GetSubBreedImageUseCase

internal class SubBreedViewModel(
    savedStateHandle: SavedStateHandle,
    private val getSubBreedImageUseCase: GetSubBreedImageUseCase
) : ViewModel() {
    private val breedName = requireNotNull(savedStateHandle.get<String>(breedNameArgs))
    private val subBreedName = requireNotNull(savedStateHandle.get<String>(subBreedNameArgs))

    private val _uiState = MutableStateFlow<SubBreedUIState>(SubBreedUIState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _appBarTitle = MutableStateFlow("")
    val appBarTitle: StateFlow<String> = _appBarTitle

    init {
        _appBarTitle.update { "${breedName.toTitleCase()} ${subBreedName.toTitleCase()}" }
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            getSubBreedImageUseCase(breedName, subBreedName)
                .onSuccess { result ->
                    _uiState.update { SubBreedUIState.Success(result) }
                }
                .onFailure {
                    _uiState.update { SubBreedUIState.Error("something went wrong") }
                }
        }
    }
}