package com.tobioyelekan.dogbreed.core.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tobioyelekan.dogbreed.core.designsystem.R

@Composable
fun DogBreedItem(
    breedName: String,
    imgUrl: String,
    onBreedClicked: () -> Unit
) {
    val model = ImageRequest.Builder(LocalContext.current).data(imgUrl).crossfade(true).build()

    Box {
        AsyncImage(
            model = model,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_dog_placeholder),
            error = painterResource(id = R.drawable.ic_dog_placeholder),
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { onBreedClicked() }
        )
        Text(
            modifier = Modifier.padding(20.dp),
            text = breedName,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.White
            )
        )
    }
}