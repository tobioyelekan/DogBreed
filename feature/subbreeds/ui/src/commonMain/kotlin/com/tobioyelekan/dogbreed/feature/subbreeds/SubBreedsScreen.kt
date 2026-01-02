package com.tobioyelekan.dogbreed.feature.subbreeds//package com.tobioyelekan.dogbreed.features.breedDetails.presentation.subbreeds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.tobioyelekan.dogbreed.core.model.SubBreedImage
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SubBreedsScreen(
    onBackClicked: () -> Unit,
    viewModel: SubBreedViewModel,
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    val appBarTitle by viewModel.appBarTitle.collectAsStateWithLifecycle()

    SubBreedScreenContent(
        appBarTitle = appBarTitle,
        viewState = viewState,
        onBackClicked = onBackClicked
    )
}

@Composable
internal fun SubBreedScreenContent(
    appBarTitle: String,
    viewState: SubBreedUIState,
    onBackClicked: () -> Unit
) {
    DogAppBar(
        title = appBarTitle,
        onBackClicked = onBackClicked
    ) {
        viewState.let { state ->
            when (state) {
                is SubBreedUIState.Success -> SubBreedsListContent(subBreeds = state.images)
                SubBreedUIState.Loading -> LoadingIndicator()
                is SubBreedUIState.Error -> ErrorState(text = state.message)
            }
        }
    }
}

@Composable
private fun SubBreedsListContent(
    subBreeds: List<SubBreedImage>
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 10.dp
    ) {
        items(subBreeds) { item ->
            val model =
                ImageRequest.Builder(LocalPlatformContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build()

            AsyncImage(
                model = model,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(Res.drawable.ic_dog_placeholder),
                error = painterResource(Res.drawable.ic_dog_placeholder),
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10.dp))
                    .testTag("subBreedImageItem")
            )
        }
    }
}