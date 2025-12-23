package com.tobioyelekan.dogbreed.feature.allbreeds.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tobioyelekan.dogbreed.feature.allbreeds.AllBreedsScreen

const val allBreedRoute = "all_breed_route"

fun NavGraphBuilder.allBreedRoute(onBreedClicked: (String) -> Unit) {
    composable(allBreedRoute) {
        AllBreedsScreen(onBreedClicked)
    }
}