package com.tobioyelekan.dogbreed.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tobioyelekan.dogbreed.feature.allbreeds.navigation.allBreedRoute
import com.tobioyelekan.dogbreed.feature.breedDetails.navigation.navigateToBreedDetails
import com.tobioyelekan.dogbreed.feature.favorites.navigation.favoritesBreedRoute

@Composable
fun DogBreedBottomNavComposeDestinations(
    bottomBarNavController: NavHostController,
    mainNavController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = bottomBarNavController,
        startDestination = allBreedRoute,
        modifier = Modifier.padding(innerPadding)
    ) {
        allBreedRoute(mainNavController::navigateToBreedDetails)
        favoritesBreedRoute(mainNavController::navigateToBreedDetails)
    }
}