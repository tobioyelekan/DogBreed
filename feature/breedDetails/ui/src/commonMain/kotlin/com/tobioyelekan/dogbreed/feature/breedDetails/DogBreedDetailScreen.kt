package com.tobioyelekan.dogbreed.feature.breedDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.tobioyelekan.dogbreed.core.designsystem.Res
import com.tobioyelekan.dogbreed.core.designsystem.components.DogAppBar
import com.tobioyelekan.dogbreed.core.designsystem.components.ErrorState
import com.tobioyelekan.dogbreed.core.designsystem.components.LoadingIndicator
import com.tobioyelekan.dogbreed.core.designsystem.ic_dog_placeholder
import com.tobioyelekan.dogbreed.core.designsystem.theme.DogBreedTheme
import com.tobioyelekan.dogbreed.core.model.DogBreed
import com.tobioyelekan.dogbreed.feature.breedDetails.DogBreedDetailsViewModel.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun DogBreedDetailScreen(
    onSubBreedClicked: (breedName: String, subBreedName: String) -> Unit,
    onBackClicked: () -> Unit,
    viewModel: DogBreedDetailsViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    val appBarTitle by viewModel.appBarTitle.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.actionState.collect {
            when (it) {
                is ActionState.ShowMessage -> {
                    //TODO(tired)
//                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    DogBreedDetailsScreenContent(
        appBarTitle = appBarTitle,
        viewState = viewState,
        onBackClicked = onBackClicked,
        onFavoriteClicked = viewModel::onFavoriteClicked,
        onSubBreedClicked = onSubBreedClicked
    )
}

@Composable
internal fun DogBreedDetailsScreenContent(
    appBarTitle: String,
    viewState: DogBreedDetailsUIState,
    onBackClicked: () -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    onSubBreedClicked: (String, String) -> Unit
) {
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    DogAppBar(
        title = appBarTitle,
        onBackClicked = onBackClicked,
        actions = {
            IconButton(
                modifier = Modifier.testTag("favoriteIconButton"),
                onClick = { onFavoriteClicked(isFavorite) }
            ) {
                Icon(
                    imageVector = if (isFavorite)
                        Icons.Filled.Favorite
                    else
                        Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite)
                        "click to remove breed as favorite"
                    else
                        "click to add breed as favorite"
                )
            }
        }
    ) {
        viewState.let { state ->
            when (state) {
                DogBreedDetailsUIState.Loading -> LoadingIndicator()
                is DogBreedDetailsUIState.Success -> {
                    DogBreedDetailsContent(state.breedDetails) {
                        onSubBreedClicked(state.breedDetails.name, it)
                    }.also {
                        isFavorite = state.breedDetails.isFavorite
                    }
                }

                is DogBreedDetailsUIState.Error -> ErrorState(text = state.message)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun DogBreedDetailsContent(
    details: DogBreed,
    onSubBreedClicked: (subBreedName: String) -> Unit
) {
    val model =
        ImageRequest.Builder(LocalPlatformContext.current)
            .data(details.imageUrl)
            .crossfade(true)
            .build()

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        AsyncImage(
            model = model,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(Res.drawable.ic_dog_placeholder),
            error = painterResource(Res.drawable.ic_dog_placeholder),
            modifier = Modifier
                .height(200.dp)
                .testTag("image")
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(Modifier.padding(16.dp)) {

            if (details.subBreeds.isEmpty()) {
                Text(text = "No sub breeds listed")
            } else {
                Text(text = "Sub breeds")

                Spacer(modifier = Modifier.height(10.dp))

                FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    repeat(details.subBreeds.size) {
                        SuggestionChip(
                            modifier = Modifier.testTag("subbreedItem"),
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                labelColor = MaterialTheme.colorScheme.primary
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                            onClick = { onSubBreedClicked(details.subBreeds[it]) },
                            label = { Text(text = details.subBreeds[it]) })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDogBreedDetailsScreenContent() {
    DogBreedTheme {
        DogBreedDetailsScreenContent(
            appBarTitle = "title",
            viewState = DogBreedDetailsUIState.Success(
                DogBreed("Breed", "", listOf("one", "two"), true)
            ),
            onBackClicked = {},
            onFavoriteClicked = {},
            onSubBreedClicked = { a, b -> }
        )
    }
}