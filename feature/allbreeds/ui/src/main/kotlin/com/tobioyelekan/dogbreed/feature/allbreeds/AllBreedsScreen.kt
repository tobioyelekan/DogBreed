package com.tobioyelekan.dogbreed.feature.allbreeds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tobioyelekan.dogbreed.core.designsystem.components.DogBreedItem
import com.tobioyelekan.dogbreed.core.designsystem.components.ErrorState
import com.tobioyelekan.dogbreed.core.designsystem.components.LoadingIndicator
import com.tobioyelekan.dogbreed.core.designsystem.theme.DogBreedTheme
import com.tobioyelekan.dogbreed.core.model.DogBreed

@Composable
fun AllBreedsScreen(
    onBreedClicked: (String) -> Unit,
    viewModel: AllBreedsViewModel = hiltViewModel()
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    AllBreedScreenContent(
        viewState = viewState,
        onBreedClicked = onBreedClicked
    )
}

@Composable
internal fun AllBreedScreenContent(
    viewState: AllBreedsUiState,
    onBreedClicked: (breedName: String) -> Unit
) {
    viewState.let { state ->
        when (state) {
            AllBreedsUiState.Loading -> LoadingIndicator()
            is AllBreedsUiState.Success -> {
                AllBreedListContent(
                    breeds = state.dogBreeds,
                    onBreedClicked
                )
            }

            is AllBreedsUiState.Error -> ErrorState(text = state.message)
        }
    }
}

@Composable
private fun AllBreedListContent(
    breeds: List<DogBreed>,
    onBreedClicked: (breedName: String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()
        ) {
            items(items = breeds, key = { it.name }) {
                DogBreedItem(
                    breedName = it.name,
                    imgUrl = it.imageUrl
                ) {
                    onBreedClicked(it.name)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAllBreedListContent() {
    DogBreedTheme {
        AllBreedListContent(
            breeds = listOf(
                DogBreed("DogBreed", imageUrl = "", subBreeds = emptyList(), false)
            ),
            onBreedClicked = {}
        )

    }
}