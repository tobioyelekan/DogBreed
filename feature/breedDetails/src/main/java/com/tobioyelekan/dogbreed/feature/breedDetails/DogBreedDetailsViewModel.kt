package com.tobioyelekan.dogbreed.feature.breedDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tobioyelekan.dogbreed.core.common.util.toTitleCase
import com.tobioyelekan.dogbreed.domain.breedDetails.AddFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.domain.breedDetails.DeleteFavoriteBreedUseCase
import com.tobioyelekan.dogbreed.domain.breedDetails.GetBreedDetailsUseCase
import com.tobioyelekan.dogbreed.feature.breedDetails.navigation.breedNameArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getBreedDetailsUseCase: GetBreedDetailsUseCase,
    private val addFavoriteBreedUseCase: AddFavoriteBreedUseCase,
    private val deleteFavoriteBreedUseCase: DeleteFavoriteBreedUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val breedName =
        requireNotNull(savedStateHandle.get<String>(breedNameArgs))

    private val _actionState = MutableSharedFlow<ActionState>()
    val actionState: SharedFlow<ActionState> = _actionState

    private val _appBarTitle = MutableStateFlow("")
    val appBarTitle = _appBarTitle.asStateFlow()

    init {
        _appBarTitle.update { breedName.toTitleCase() }
    }

    val uiState: StateFlow<DogBreedDetailsUIState> =
        getBreedDetailsUseCase(breedName)
            .map { result ->
                result.fold(
                    onSuccess = { DogBreedDetailsUIState.Success(it) },
                    onFailure = {
                        DogBreedDetailsUIState.Error("Something went wrong")
                    }
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DogBreedDetailsUIState.Loading
            )

    fun onFavoriteClicked(isFavorite: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            val result = if (isFavorite) {
                deleteFavoriteBreedUseCase(breedName)
            } else {
                addFavoriteBreedUseCase(breedName)
            }

            result
                .onSuccess {
                    val msg = if (isFavorite) "Removed as favorite" else "Added as favorite"
                    _actionState.emit(ActionState.ShowMessage(msg))
                }
                .onFailure {
                    _actionState.emit(ActionState.ShowMessage("Something went wrong"))
                }
        }
    }

    sealed class ActionState {
        class ShowMessage(val message: String) : ActionState()
    }
}