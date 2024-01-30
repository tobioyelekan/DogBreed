package com.tobioyelekan.dogbreed.feature.subbreeds

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobioyelekan.dogbreed.domain.subbreeds.GetSubBreedImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.tobioyelekan.dogbreed.core.common.result.Result
import com.tobioyelekan.dogbreed.core.common.util.toTitleCase
import com.tobioyelekan.dogbreed.feature.subbreeds.navigation.breedNameArgs
import com.tobioyelekan.dogbreed.feature.subbreeds.navigation.subBreedNameArgs

@HiltViewModel
class SubBreedViewModel @Inject constructor(
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
            when (val result = getSubBreedImageUseCase(breedName, subBreedName)) {
                is Result.Success -> _uiState.update { SubBreedUIState.Success(result.value) }
                is Result.Failure -> _uiState.update { SubBreedUIState.Error(result.errorMessage) }
            }
        }
    }
}