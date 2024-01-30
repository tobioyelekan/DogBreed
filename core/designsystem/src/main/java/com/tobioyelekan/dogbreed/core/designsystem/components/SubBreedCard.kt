package com.tobioyelekan.dogbreed.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tobioyelekan.dogbreed.core.designsystem.R

@Composable
fun SubBreedCard(imageUrl: String, subBreedName: String) {
    val model =
        ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true).build()

    Card(border = BorderStroke(1.dp, Color.Gray)) {
        Row {
            AsyncImage(
                model = model,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_dog_placeholder),
                error = painterResource(id = R.drawable.ic_dog_placeholder),
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                Text(subBreedName)

                Spacer(modifier = Modifier.height(15.dp))

                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(text = "Add as Favorite")

                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}