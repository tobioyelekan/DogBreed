package com.tobioyelekan.dogbreed.feature.breedDetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tobioyelekan.dogbreed.feature.breedDetails.DogBreedDetailScreen
import com.tobioyelekan.dogbreed.feature.breedDetails.DogBreedDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

const val breedNameArgs = "breedName"
const val breedDetailsRoute = "breed_details_route/{$breedNameArgs}"

fun NavController.navigateToBreedDetails(breedName: String) {
    this.navigate("breed_details_route/${breedName}")
}

fun NavGraphBuilder.breedDetailsRoute(
    onSubBreedClicked: (String, String) -> Unit,
    onBackClicked: () -> Unit
) {
    composable(breedDetailsRoute) {
        DogBreedDetailScreen(
            onSubBreedClicked = onSubBreedClicked,
            onBackClicked = onBackClicked,
            viewModel = koinViewModel<DogBreedDetailsViewModel>()
        )
    }
}