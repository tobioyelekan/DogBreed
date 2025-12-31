package com.tobioyelekan.dogbreed.feature.allbreeds.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tobioyelekan.dogbreed.feature.allbreeds.AllBreedsScreen
import com.tobioyelekan.dogbreed.feature.allbreeds.AllBreedsViewModel
import org.koin.compose.viewmodel.koinViewModel

const val allBreedRoute = "all_breed_route"

fun NavGraphBuilder.allBreedRoute(onBreedClicked: (String) -> Unit) {
    composable(allBreedRoute) {
        AllBreedsScreen(
            onBreedClicked = onBreedClicked,
            viewModel = koinViewModel<AllBreedsViewModel>()
        )
    }
}