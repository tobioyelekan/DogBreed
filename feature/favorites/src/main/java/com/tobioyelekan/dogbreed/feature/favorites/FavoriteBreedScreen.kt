package com.tobioyelekan.dogbreed.feature.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tobioyelekan.dogbreed.core.designsystem.components.DogBreedItem
import com.tobioyelekan.dogbreed.core.designsystem.components.ErrorState
import com.tobioyelekan.dogbreed.core.designsystem.components.LoadingIndicator
import com.tobioyelekan.dogbreed.core.designsystem.theme.DogBreedTheme
import com.tobioyelekan.dogbreed.core.model.DogBreed

@Composable
internal fun FavoriteBreedScreen(
    onBreedClicked: (String) -> Unit,
    viewModel: FavoriteBreedViewModel = hiltViewModel(),
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    FavoriteBreedScreenContent(
        viewState = viewState,
        onBreedClicked = onBreedClicked
    )
}

@Composable
internal fun FavoriteBreedScreenContent(
    viewState: FavoriteBreedUIState,
    onBreedClicked: (breedName: String) -> Unit
) {
    viewState.let { state ->
        when (state) {
            FavoriteBreedUIState.Loading -> LoadingIndicator()
            is FavoriteBreedUIState.Success -> {
                FavoriteBreedListContent(
                    breeds = state.favoriteBreeds,
                    onBreedClicked = onBreedClicked
                )
            }

            is FavoriteBreedUIState.Error -> ErrorState(text = state.message)
        }
    }
}

@Composable
private fun FavoriteBreedListContent(
    breeds: List<DogBreed>,
    onBreedClicked: (breedName: String) -> Unit
) {
    if (breeds.isEmpty())
        EmptyState()
    else
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

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("emptyState"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No favorites breed found \nClick the fav icon on dog details screen",
            style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center)
        )
    }
}

@Preview
@Composable
fun PreviewFavoriteBreedListContent() {
    DogBreedTheme {
        FavoriteBreedListContent(
            breeds = listOf(
                DogBreed("DogBreed", imageUrl = "", subBreeds = emptyList(), false)
            ),
            onBreedClicked = {}
        )
    }
}